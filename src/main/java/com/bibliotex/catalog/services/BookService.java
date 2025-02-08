package com.bibliotex.catalog.services;

import com.bibliotex.catalog.domain.dto.request.BookRequest;
import com.bibliotex.catalog.domain.dto.response.BookResponse;
import com.bibliotex.catalog.domain.model.Book;
import com.bibliotex.catalog.exceptions.BookNotFoundException;
import com.bibliotex.catalog.mappers.BookMapper;
import com.bibliotex.catalog.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final ValidatorService validatorService;
    private final GenreService genreService;

    public BookResponse create(BookRequest bookRequest) {
        validatorService.isValidOrThrow(bookRequest);

        var authors = authorService.findById(bookRequest.authorsIds());
        var genres = genreService.findById(bookRequest.genresIds());
        var publisher = publisherService.findById(bookRequest.publisherId());

        Book book = bookMapper.toEntity(bookRequest, authors, publisher, genres);

        book = bookRepository.save(book);

        return bookMapper.toDTO(book);
    }

    public List<BookResponse> findAll() {
        return bookRepository.findAll().stream().map(bookMapper::toDTO).toList();
    }

    public Book findActive(Long id) {
        return bookRepository.findByIdAndIsActiveTrue(id).orElseThrow(BookNotFoundException::new);
    }

    public Book find(Long id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public BookResponse findBy(Long id) {
        Book book = this.findActive(id);

        return bookMapper.toDTO(book);
    }

    public void deleteById(Long id) {
        Book book = this.find(id);

        book.setIsActive(false);
        book.setDeletedAt(LocalDateTime.now());

        bookRepository.save(book);
    }

    public void restoreBy(Long id) {
        Book book = this.find(id);

        book.setIsActive(true);
        book.setDeletedAt(null);

        bookRepository.save(book);
    }
}

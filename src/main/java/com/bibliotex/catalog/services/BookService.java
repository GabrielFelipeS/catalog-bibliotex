package com.bibliotex.catalog.services;

import com.bibliotex.catalog.domain.dto.request.BookRequest;
import com.bibliotex.catalog.domain.dto.response.BookResponse;
import com.bibliotex.catalog.domain.model.Book;
import com.bibliotex.catalog.exceptions.BookNotFoundException;
import com.bibliotex.catalog.mappers.BookMapper;
import com.bibliotex.catalog.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final ImageValidationService imageValidationService;

    public BookResponse createBook(BookRequest bookRequest) {
        boolean isNotValidUrlImg = imageValidationService.isInvalid(bookRequest.imageUrl());

        if (isNotValidUrlImg) throw new IllegalStateException("A imagem fornecida não existe");

        boolean isbnAlreadyExists = bookRepository.existsBookByIsbn(bookRequest.isbn());

        if (isbnAlreadyExists) throw new IllegalStateException("Isbn já existe");

        var authors = authorService.findById(bookRequest.authorsIds());
        var publisher = publisherService.findById(bookRequest.publisherId());

        Book book = bookMapper.toEntity(bookRequest, authors, publisher);

        book = bookRepository.save(book);

        return bookMapper.toDTO(book);
    }

    public List<BookResponse> findAll() {
        return bookRepository.findAll().stream().map(bookMapper::toDTO).toList();
    }

    public BookResponse findBy(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);

        return bookMapper.toDTO(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}

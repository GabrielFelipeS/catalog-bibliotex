package com.bibliotex.catalog.mappers;

import com.bibliotex.catalog.domain.dto.request.BookRequest;
import com.bibliotex.catalog.domain.dto.response.BookResponse;
import com.bibliotex.catalog.domain.model.Author;
import com.bibliotex.catalog.domain.model.Book;
import com.bibliotex.catalog.domain.model.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMapper {

    public Book toEntity(BookRequest bookRequest, List<Author> authors, Publisher publisher) {
        return new Book(
                bookRequest.title(),
                bookRequest.description(),
                bookRequest.pages(),
                bookRequest.language(),
                bookRequest.edition(),
                bookRequest.yearOfRelease(),
                bookRequest.imageUrl(),
                bookRequest.isbn().replaceAll("[^0-9]", ""),
                authors,
                publisher
        );
    }

    public BookResponse toDTO(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getDescription(),
                book.getPages(),
                book.getLanguage(),
                book.getEdition(),
                book.getYearOfRelease(),
                book.getImageUrl(),
                book.getIsbn(),
                book.getAuthors(),
                book.getPublisher()
        );
    }
}

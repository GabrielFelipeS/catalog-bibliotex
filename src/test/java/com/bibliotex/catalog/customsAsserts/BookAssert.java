package com.bibliotex.catalog.customsAsserts;

import com.bibliotex.catalog.domain.dto.request.BookRequest;
import com.bibliotex.catalog.domain.model.Book;
import org.assertj.core.api.AbstractAssert;

public class BookAssert extends AbstractAssert<BookAssert, Book> {
    private BookRequest bookRequest;

    protected BookAssert(Book actual) {
        super(actual, BookAssert.class);
    }

    public static BookAssert assertThat(Book book) {
        return new BookAssert(book);
    }

    public BookAssert hasIsbnNoSpecialCharacter(String isbn) {
        isNotNull();

        String actualIsbn = actual.getIsbn();
        String expectedISBN = isbn.replaceAll("[^0-9]", "");

        if (!actual.getIsbn().equals(expectedISBN)) {
            failWithMessage("Esperava que o isbn fosse %s, mas era %s", expectedISBN, actualIsbn);
        }

        return this;
    }
}

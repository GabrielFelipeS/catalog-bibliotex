package com.bibliotex.catalog.customsAsserts;

import com.bibliotex.catalog.domain.dto.response.BookResponse;
import org.assertj.core.api.AbstractAssert;

public class BookAssert extends AbstractAssert<BookAssert, BookResponse> {

    protected BookAssert(BookResponse actual) {
        super(actual, BookAssert.class);
    }

    public static BookAssert assertThat(BookResponse book) {
        return new BookAssert(book);
    }

    public BookAssert hasIsbnNoSpecialCharacter(String isbn) {
        isNotNull();

        String actualIsbn = actual.isbn();
        String expectedISBN = isbn.replaceAll("[^0-9]", "");

        if (!actualIsbn.equals(expectedISBN)) {
            failWithMessage("Esperava que o isbn fosse %s, mas era %s", expectedISBN, actualIsbn);
        }

        return this;
    }
}

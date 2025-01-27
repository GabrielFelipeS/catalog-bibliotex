package com.bibliotex.catalog.exceptions;

import java.util.NoSuchElementException;

public class BookNotFoundException extends NoSuchElementException {
    public BookNotFoundException() {
        super("Livro não encontrado");
    }
}

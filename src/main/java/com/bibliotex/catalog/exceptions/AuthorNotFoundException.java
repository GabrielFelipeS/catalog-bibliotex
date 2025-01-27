package com.bibliotex.catalog.exceptions;

import java.util.NoSuchElementException;

public class AuthorNotFoundException extends NoSuchElementException {
    public AuthorNotFoundException() {
        super("Author não encontrado");
    }
}

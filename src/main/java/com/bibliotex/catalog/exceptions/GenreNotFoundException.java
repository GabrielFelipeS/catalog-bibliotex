package com.bibliotex.catalog.exceptions;

import java.util.NoSuchElementException;

public class GenreNotFoundException extends NoSuchElementException {
    public GenreNotFoundException() {
        super("Gênero não encontrado");
    }
}

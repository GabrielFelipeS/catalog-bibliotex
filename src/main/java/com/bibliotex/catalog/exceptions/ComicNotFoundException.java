package com.bibliotex.catalog.exceptions;

import java.util.NoSuchElementException;

public class ComicNotFoundException extends NoSuchElementException {
    public ComicNotFoundException() {
        super("Quadrinho não encontrado");
    }
}

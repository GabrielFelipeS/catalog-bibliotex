package com.bibliotex.catalog.exceptions;

import java.util.NoSuchElementException;

public class PublisherNotFoundException extends NoSuchElementException {
    public PublisherNotFoundException() {
        super("Editora não encontrada");
    }
}

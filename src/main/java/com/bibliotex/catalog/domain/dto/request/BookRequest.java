package com.bibliotex.catalog.domain.dto.request;

import com.bibliotex.catalog.domain.model.Author;
import com.bibliotex.catalog.domain.model.Publisher;


import java.util.List;


public record BookRequest (
    String title,
    String description,
    Integer pages,
    String languague,
    Integer edtiion,
    Integer yearOfRelease,
    List<Author> authors,
    Publisher publisher,
    String isbn
) { }

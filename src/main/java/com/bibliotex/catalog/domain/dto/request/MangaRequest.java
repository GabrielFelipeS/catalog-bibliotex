package com.bibliotex.catalog.domain.dto.request;

import com.bibliotex.catalog.domain.model.Author;
import com.bibliotex.catalog.domain.model.Publisher;

import java.util.List;


public record MangaRequest (
    String title,
    String description,
    Integer pages,
    String language,
    Integer edition,
    Integer yearOfRelease,
    List<Author> authors,
    Publisher publisher,
    Boolean isOngoing,
    String magazine,
    String imageUrl
) { }

package com.bibliotex.catalog.domain.dto.response;

import com.bibliotex.catalog.domain.model.Author;
import com.bibliotex.catalog.domain.model.Publisher;

import java.util.List;

public record MangaResponse(
        Long id,
        String title,
        String description,
        Integer pages,
        String language,
        Integer edition,
        Integer yearOfRelease,
        String imageUrl,
        Boolean isOngoing,
        String magazine,
        List<Author> authors,
        Publisher publisher
) {
}

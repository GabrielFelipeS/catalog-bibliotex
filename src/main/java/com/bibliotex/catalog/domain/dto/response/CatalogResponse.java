package com.bibliotex.catalog.domain.dto.response;

import com.bibliotex.catalog.domain.model.Author;
import com.bibliotex.catalog.domain.model.Publisher;

import java.util.List;

public record CatalogResponse(
        Long id,
        String title,
        String description,
        Integer pages,
        String language,
        Integer edition,
        Integer yearOfRelease,
        String imageUrl,
        List<Author> authors,
        Publisher publisher
) {
}

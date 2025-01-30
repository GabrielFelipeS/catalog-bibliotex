package com.bibliotex.catalog.mappers;

import com.bibliotex.catalog.domain.dto.request.ComicRequest;
import com.bibliotex.catalog.domain.dto.response.ComicResponse;
import com.bibliotex.catalog.domain.model.Author;
import com.bibliotex.catalog.domain.model.Comic;
import com.bibliotex.catalog.domain.model.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComicMapper {

    public Comic toEntity(ComicRequest comicRequest, List<Author> authors, Publisher publisher) {
        return new Comic(
                comicRequest.title(),
                comicRequest.description(),
                comicRequest.pages(),
                comicRequest.language(),
                comicRequest.edition(),
                comicRequest.yearOfRelease(),
                comicRequest.imageUrl(),
                authors,
                publisher,
                comicRequest.universe()
        );
    }

    public ComicResponse toDTO(Comic comic) {
        return new ComicResponse(
                comic.getId(),
                comic.getTitle(),
                comic.getDescription(),
                comic.getPages(),
                comic.getLanguage(),
                comic.getEdition(),
                comic.getYearOfRelease(),
                comic.getImageUrl(),
                comic.getAuthors(),
                comic.getPublisher(),
                comic.getUniverse()
        );
    }
}

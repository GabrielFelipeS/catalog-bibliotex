package com.bibliotex.catalog.mappers;

import com.bibliotex.catalog.domain.dto.request.MangaRequest;
import com.bibliotex.catalog.domain.dto.response.MangaResponse;
import com.bibliotex.catalog.domain.model.Author;
import com.bibliotex.catalog.domain.model.Manga;
import com.bibliotex.catalog.domain.model.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MangaMapper {

    public Manga toEntity(MangaRequest mangaRequest, List<Author> authors, Publisher publisher) {
        return new Manga(
                mangaRequest.title(),
                mangaRequest.description(),
                mangaRequest.pages(),
                mangaRequest.language(),
                mangaRequest.edition(),
                mangaRequest.yearOfRelease(),
                mangaRequest.imageUrl(),
                authors,
                publisher,
                mangaRequest.isOngoing(),
                mangaRequest.magazine()
        );
    }

    public MangaResponse toDTO(Manga manga) {
        return new MangaResponse(
                manga.getId(),
                manga.getTitle(),
                manga.getDescription(),
                manga.getPages(),
                manga.getLanguage(),
                manga.getEdition(),
                manga.getYearOfRelease(),
                manga.getImageUrl(),
                manga.getIsOnGoing(),
                manga.getMagazine(),
                manga.getAuthors(),
                manga.getPublisher()
        );
    }
}

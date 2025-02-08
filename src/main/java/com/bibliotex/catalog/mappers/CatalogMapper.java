package com.bibliotex.catalog.mappers;


import com.bibliotex.catalog.domain.dto.response.BookResponse;
import com.bibliotex.catalog.domain.dto.response.CatalogResponse;
import com.bibliotex.catalog.domain.dto.response.ComicResponse;
import com.bibliotex.catalog.domain.dto.response.MangaResponse;


public class CatalogMapper {

    public static CatalogResponse toDTO(BookResponse book) {
        return new CatalogResponse(
                book.id(),
                book.title(),
                book.description(),
                book.pages(),
                book.language(),
                book.edition(),
                book.yearOfRelease(),
                book.imageUrl(),
                book.authors(),
                book.publisher()
        );
    }

    public static CatalogResponse toDTO(ComicResponse comic) {
        return new CatalogResponse(
                comic.id(),
                comic.title(),
                comic.description(),
                comic.pages(),
                comic.language(),
                comic.edition(),
                comic.yearOfRelease(),
                comic.imageUrl(),
                comic.authors(),
                comic.publisher()
        );
    }

    public static CatalogResponse toDTO(MangaResponse manga) {
        return new CatalogResponse(
                manga.id(),
                manga.title(),
                manga.description(),
                manga.pages(),
                manga.language(),
                manga.edition(),
                manga.yearOfRelease(),
                manga.imageUrl(),
                manga.authors(),
                manga.publisher()
        );
    }
}

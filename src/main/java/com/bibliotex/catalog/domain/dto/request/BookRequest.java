package com.bibliotex.catalog.domain.dto.request;

import com.bibliotex.catalog.domain.messsages.ValidationMessages;
import jakarta.validation.constraints.*;

import java.util.List;


public record BookRequest (
        @NotBlank(message = ValidationMessages.TITLE_NOT_BLANK)
        String title,

        @NotBlank(message = ValidationMessages.DESCRIPTION_NOT_BLANK)
        String description,

        @NotNull(message = ValidationMessages.PAGES_NOT_NULL)
        @Positive(message = ValidationMessages.PAGES_QUANTITY_MUST_ZERO)
        Integer pages,

        @NotBlank(message = ValidationMessages.LANGUAGE_NOT_BLANK)
        String language,

        @NotNull(message = ValidationMessages.EDITION_NOT_NULL)
        @Positive(message = ValidationMessages.EDITION_QUANTITY_MUST_ZERO)
        Integer edition,

        @NotNull(message = ValidationMessages.YEAR_OF_RELEASE_NOT_NULL)
        @Positive(message = ValidationMessages.YEAR_OF_RELEASE_MUST_ZERO)
        Integer yearOfRelease,

        @NotEmpty(message = ValidationMessages.AUTHORS_ID_NOT_EMPTY)
        @NotNull(message = ValidationMessages.AUTHORS_IDS_NOT_NULL)
        List<@Positive(message = ValidationMessages.AUTHOR_ID_MUST_ZERO) Long> authorsIds,

        @NotNull(message = ValidationMessages.PUBLISHER_ID_NOT_NULL)
        @Positive(message = ValidationMessages.PUBLISHER_ID_MUST_ZERO)
        Long publisherId,

        @NotBlank(message = ValidationMessages.IMAGE_NOT_BLANK)
        @Pattern(message = ValidationMessages.IMAGE_VALID,
                regexp = "^http(|s)://books\\.google\\.com/books/content\\?id=[a-zA-Z0-9_-]+(&.*)?$")
        String imageUrl,

        @NotBlank(message = ValidationMessages.ISBN_NOT_BLANK)
        String isbn,

        @NotNull(message = ValidationMessages.HAS_ADAPTATION_NOT_NULL)
        Boolean hasAdaptation,

        @NotBlank(message = ValidationMessages.PUBLICATION_STATUS_NOT_BLANK)
        String publicationStatus,

        @NotBlank(message = ValidationMessages.CLASSIFICATION_NOT_BLANK)
        String classification,

        @NotNull(message = ValidationMessages.GENRE_ID_NOT_NULL)
        @NotEmpty(message = ValidationMessages.GENRES_ID_NOT_EMPTY)
        List<@Positive(message = ValidationMessages.GENRE_ID_MUST_ZERO) Long> genresIds

) { }

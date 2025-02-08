package com.bibliotex.catalog.domain.dto.request;


import com.bibliotex.catalog.domain.messsages.ValidationMessages;
import jakarta.validation.constraints.*;

import java.util.List;

public record ComicRequest (
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

        @NotBlank(message = ValidationMessages.UNVERSE_NOT_BLANK)
        String universe,

        @NotBlank(message = "A imageUrl não deveria ser nulo ou vazio")
        @Pattern(regexp = "^http(|s)://books\\.google\\.com/books/content\\?id=[a-zA-Z0-9_-]+(&.*)?$", message = "O endereço da imagem não é valido, por favor insira outro")
    String imageUrl
) { }

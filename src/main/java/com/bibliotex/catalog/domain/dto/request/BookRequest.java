package com.bibliotex.catalog.domain.dto.request;

import jakarta.validation.constraints.*;

import java.util.List;


public record BookRequest (
        @NotBlank(message = "O titulo não deveria ser nulo ou vazio")
    String title,

        @NotBlank(message = "A descrição não deveria ser nula ou vazia")
    String description,

        @NotNull(message = "A páginas não deve ser nulo")
        @Positive(message = "A quantidade de páginas deve ser maior que zero")
    Integer pages,

        @NotBlank(message = "A lingua não deveria ser nula ou vazia")
        String language,

        @NotNull(message = "A edição não deve ser nulo")
        @Positive(message = "A edição deve ser maior que zero")
        Integer edition,

        @NotNull(message = "O ano de lançamento não deve ser nulo")
        @Positive(message = "A quantidade de páginas deve ser maior que zero")
    Integer yearOfRelease,

        @NotEmpty(message = "A lista de IDs de autores não deve ser vazia")
        @NotNull(message = "A lista de IDs dos autores não deve ser nulo")
        List<@Positive(message = "O id do autor deve ser maior que zero") Long> authorsIds,

        @NotNull(message = "A editora não deve ser nulo")
        @Positive(message = "A quantidade de páginas deve ser maior que zero")
        Long publisherId,

        @NotBlank(message = "A imageUrl não deveria ser nulo ou vazio")
        @Pattern(regexp = "^http(|s)://books\\.google\\.com/books/content\\?id=[a-zA-Z0-9_-]+(&.*)?$", message = "O endereço da imagem não é valido, por favor insira outro")
        String imageUrl,

        @NotBlank(message = "O ISBN não deveria ser nulo ou vazio")
        String isbn

) { }

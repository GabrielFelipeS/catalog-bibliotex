package com.bibliotex.catalog.domain.messsages;

public final class ValidationMessages {
    public static final String TITLE_NOT_BLANK = "O titulo não pode ser nulo ou vazio";
    public static final String ORIGINAL_TITLE_NOT_BLANK = "O titulo original não pode ser nulo ou vazio";
    public static final String DESCRIPTION_NOT_BLANK = "A descrição não pode ser nula ou vazia";
    public static final String LANGUAGE_NOT_BLANK = "A lingua não pode ser nula ou vazia";
    public static final String IMAGE_NOT_BLANK = "A imagem não pode ser nula ou vazia";
    public static final String PUBLICATION_STATUS_NOT_BLANK = "O status de publicação não pode ser nula ou vazia";
    public static final String CLASSIFICATION_NOT_BLANK = "A classificação não deve ser nula ou vazia";
    public static final String ISBN_NOT_BLANK = "O ISBN não deve ser nulo ou vazio";
    public static final String UNVERSE_NOT_BLANK = "O universo não deve ser nulo ou vazio";

    public static final String PAGES_QUANTITY_MUST_ZERO = "A quantidade de páginas deve ser maior que zero";
    public static final String EDITION_QUANTITY_MUST_ZERO = "A edição deve ser maior que zero";
    public static final String YEAR_OF_RELEASE_MUST_ZERO = "O ano de lançamento deve ser maior que zero";
    public static final String PUBLISHER_ID_MUST_ZERO = "O id da editora deve ser maior que zero";
    public static final String AUTHOR_ID_MUST_ZERO = "O id do autor deve ser maior que zero";
    public static final String GENRE_ID_MUST_ZERO = "O id do gênero deve ser maior que zero";

    public static final String HAS_ADAPTATION_NOT_NULL = "O status de tem adaptação não deve ser nulo.";
    public static final String PAGES_NOT_NULL = "A quantidade de páginas não deve ser nulo.";
    public static final String YEAR_OF_RELEASE_NOT_NULL = "O ano de lançamento não deve ser nulo.";
    public static final String AUTHORS_IDS_NOT_NULL = "A lista de IDs dos autores não deve ser nulo.";
    public static final String PUBLISHER_ID_NOT_NULL = "A editora não deve ser nula.";
    public static final String GENRE_ID_NOT_NULL = "A lista de IDs dos gêneros não deve ser nula.";
    public static final String EDITION_NOT_NULL = "A edição não pode ser nula";

    public static final String AUTHORS_ID_NOT_EMPTY = "A lista de IDs dos autores não deve estar vazia.";
    public static final String GENRES_ID_NOT_EMPTY = "A lista de IDs dos gêneros não deve estar vazia.";

    public static final String IMAGE_VALID = "O endereço da imagem não é valido, por favor insira outro";

    private ValidationMessages() {
    }
}

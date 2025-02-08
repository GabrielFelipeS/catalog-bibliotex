package com.bibliotex.catalog.controllers;

import com.bibliotex.catalog.TokenProviderIT;
import com.bibliotex.catalog.customsAsserts.CatalogAssert;
import com.bibliotex.catalog.domain.dto.request.MangaRequest;
import com.bibliotex.catalog.domain.messsages.ValidationMessages;
import com.bibliotex.catalog.domain.model.Manga;
import com.bibliotex.catalog.services.KafkaService;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MangaControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private KafkaService kafkaService;

    private static @NotNull MangaRequest getMangaRequest(String imgUrl) {
        List<Long> authorsIds = Arrays.asList(1L, 2L);
        Long publisherId = 1L;
        MangaRequest mangaRequest = new MangaRequest(
                "Titulo Teste",
                "Naruto Uzumaki é um jovem ninja com o sonho de se tornar o Hokage, o líder de sua vila.",
                "Descição",
                700,
                "Japonês",
                1,
                1999,
                authorsIds,
                publisherId,
                imgUrl,
                false,
                "COMPLETED",
                "EVERYONE",
                List.of(1L, 2L)
        );

        return mangaRequest;
    }

    @Test
    @DirtiesContext
    @DisplayName("Should be able create new book with valid book values")
    void shouldBeAbleCreateBook() {
        MangaRequest mangaRequest = getMangaRequest("http://books.google.com/books/content?id=r5mdDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api");

        ResponseEntity<String> response = testRestTemplate.exchange("/catalog/manga/", HttpMethod.POST, this.getEntity(mangaRequest), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        String location = Objects.requireNonNull(response.getHeaders().getLocation()).getPath();

        response = testRestTemplate.getForEntity(location, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Manga manga = documentContext.read("$.data", Manga.class);

        CatalogAssert.assertThat(manga)
                .idNotNull()
                .isValid(mangaRequest)
        ;
    }

    @Test
    @DirtiesContext
    @DisplayName("Should be not able create new book with valid book values")
    void shouldBeNotAbleCreateBookWhenPublisherNotExists() {
        List<Long> authorsIds = List.of(1L);
        Long publisherId = 100L;

        MangaRequest mangaRequest = new MangaRequest(
                "Titulo Teste",
                "Naruto Uzumaki é um jovem ninja com o sonho de se tornar o Hokage, o líder de sua vila.",
                "Descição",
                700,
                "Japonês",
                1,
                1999,
                authorsIds,
                publisherId,
                "http://books.google.com/books/content?id=THsEEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
                false,
                "COMPLETED",
                "EVERYONE",
                List.of(1L, 2L)
        );

        ResponseEntity<String> response = testRestTemplate.exchange("/catalog/manga/", HttpMethod.POST, this.getEntity(mangaRequest), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext
    @DisplayName("Should be not able create new book with valid book values")
    void shouldBeNotAbleCreateBookWhenValuesIncorrect() {
        List<Long> authorsIds = List.of(1L, 0L, -1L);
        Long publisherId = -1L;

        MangaRequest mangaRequest = new MangaRequest(
                "",
                "",
                "",
                0,
                "",
                0,
                0,
                authorsIds,
                publisherId,
                "",
                null,
                "",
                "",
                List.of(-1L, 0L)
        );

        ResponseEntity<String> response = testRestTemplate.exchange("/catalog/manga/", HttpMethod.POST, this.getEntity(mangaRequest), String.class);
        System.err.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        DocumentContext documentContext = JsonPath.parse(response.getBody());

        List<String> errorsTitle = documentContext.read("$.title");
        List<String> errorsOriginalTitle = documentContext.read("$.originalTitle");
        List<String> errorsDescription = documentContext.read("$.description");
        List<String> errorsPages = documentContext.read("$.pages");
        List<String> errorsLanguage = documentContext.read("$.language");
        List<String> errorsEdition = documentContext.read("$.edition");
        List<String> errorsYearOfRelease = documentContext.read("$.yearOfRelease");
        List<String> errorsPublisherId = documentContext.read("$.publisherId");
        List<String> errorsImageUrl = documentContext.read("$.imageUrl");
        List<String> errorsAuthorsIds1 = documentContext.read("$.['authorsIds[1]']");
        List<String> errorsAuthorsIds2 = documentContext.read("$.['authorsIds[2]']");
        List<String> errorsGenresIds1 = documentContext.read("$.['genresIds[0]']");
        List<String> errorsGenresIds2 = documentContext.read("$.['genresIds[1]']");
        List<String> errorsHasAdaptation = documentContext.read("$.hasAdaptation");
        List<String> errorsPublicationStatus = documentContext.read("$.publicationStatus");
        List<String> errorsClassification = documentContext.read("$.classification");

        assertThat(errorsTitle).containsExactlyInAnyOrder(ValidationMessages.TITLE_NOT_BLANK);
        assertThat(errorsOriginalTitle).containsExactlyInAnyOrder(ValidationMessages.ORIGINAL_TITLE_NOT_BLANK);
        assertThat(errorsDescription).containsExactlyInAnyOrder(ValidationMessages.DESCRIPTION_NOT_BLANK);
        assertThat(errorsPages).containsExactlyInAnyOrder(ValidationMessages.PAGES_QUANTITY_MUST_ZERO);
        assertThat(errorsLanguage).containsExactlyInAnyOrder(ValidationMessages.LANGUAGE_NOT_BLANK);
        assertThat(errorsEdition).containsExactlyInAnyOrder(ValidationMessages.EDITION_QUANTITY_MUST_ZERO);
        assertThat(errorsYearOfRelease).containsExactlyInAnyOrder(ValidationMessages.YEAR_OF_RELEASE_MUST_ZERO);
        assertThat(errorsPublisherId).containsExactlyInAnyOrder(ValidationMessages.PUBLISHER_ID_MUST_ZERO);
        assertThat(errorsImageUrl).containsExactlyInAnyOrder(ValidationMessages.IMAGE_NOT_BLANK, ValidationMessages.IMAGE_VALID);
        assertThat(errorsAuthorsIds1).containsExactlyInAnyOrder(ValidationMessages.AUTHOR_ID_MUST_ZERO);
        assertThat(errorsAuthorsIds2).containsExactlyInAnyOrder(ValidationMessages.AUTHOR_ID_MUST_ZERO);

        assertThat(errorsGenresIds1).containsExactlyInAnyOrder(ValidationMessages.GENRE_ID_MUST_ZERO);
        assertThat(errorsGenresIds2).containsExactlyInAnyOrder(ValidationMessages.GENRE_ID_MUST_ZERO);
        assertThat(errorsHasAdaptation).containsExactlyInAnyOrder(ValidationMessages.HAS_ADAPTATION_NOT_NULL);
        assertThat(errorsPublicationStatus).containsExactlyInAnyOrder(ValidationMessages.PUBLICATION_STATUS_NOT_BLANK);
        assertThat(errorsClassification).containsExactlyInAnyOrder(ValidationMessages.CLASSIFICATION_NOT_BLANK);

        int fieldsError = documentContext.read("$.length()");

        assertThat(fieldsError).isEqualTo(16);
    }

    @Test
    @DirtiesContext
    @DisplayName("Should be not able create new book with valid book values")
    void shouldBeNotAbleCreateBookWhenAuthorNotExists() {
        List<Long> authorsIds = List.of(1L, 100L);
        Long publisherId = 1L;

        MangaRequest mangaRequest = new MangaRequest(
                "Titulo Teste",
                "Naruto Uzumaki é um jovem ninja com o sonho de se tornar o Hokage, o líder de sua vila.",
                "Descição",
                700,
                "Japonês",
                1,
                1999,
                authorsIds,
                publisherId,
                "http://books.google.com/books/content?id=THsEEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
                false,
                "COMPLETED",
                "EVERYONE",
                List.of(1L, 2L)
        );

        ResponseEntity<String> response = testRestTemplate.exchange("/catalog/manga/", HttpMethod.POST, this.getEntity(mangaRequest), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext
    @DisplayName("Should be not able create new book with valid book values")
    void shouldBeNotAbleCreateBook() {
        List<Long> authorsIds = List.of(1L);
        Long publisherId = 1L;

        MangaRequest mangaRequest = new MangaRequest(
                "Naruto",
                "Naruto Uzumaki é um jovem ninja com o sonho de se tornar o Hokage, o líder de sua vila.",
                "Descição",
                700,
                "Japonês",
                1,
                1999,
                authorsIds,
                publisherId,
                "http://books.google.com/books/content?id=THsEEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
                false,
                "COMPLETED",
                "EVERYONE",
                List.of(1L, 2L)
        );

        ResponseEntity<String> response = testRestTemplate.exchange("/catalog/manga/", HttpMethod.POST, this.getEntity(mangaRequest), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    @DisplayName("Should return book when id exists")
    public void findBookWhenIdExists() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/catalog/manga/4", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Manga manga = documentContext.read("$.data", Manga.class);

        CatalogAssert.assertThat(manga)
                .idNotNull()
                .idIsPositive()
                .hasTitle("Ascendance of a Bookworm")
                .hasDescription("No is the name given to him by Ginny and the only name anyone knows, he is immune to the Spread.")
                .hasPages(220)
                .hasLanguage("English")
        ;
    }

    @Test
    @DisplayName("Should return book when id does not exist")
    public void findBookWhenIdDoesNotExists() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/catalog/manga/100", String.class);
        System.err.println(response.getStatusCode());
        System.err.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Should return all books in the catalog")
    void getBooks() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/catalog/manga/", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());

        List<Manga> mangas = documentContext.read("$.data");

        assertThat(mangas).isNotEmpty();
    }

    @Test
    @DirtiesContext
    @DisplayName("Should delete book when id exists")
    public void deleteBook() {
        ResponseEntity<String> response = testRestTemplate.exchange("/catalog/manga/1", HttpMethod.DELETE, this.getEntity(), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        response = testRestTemplate.getForEntity("/catalog/manga/1", String.class);

        System.err.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @ParameterizedTest
    @CsvSource({
            "http://localhost:8080/books/1, false",
            "http://books.google.com/books/content?id=, false",
            "http://books.google.com/books/content?id=someid, false",
            "https://books.google.com/books/content?id=id_errado&printsec=errado, false",
            "http://books.google.com/books/content?id=id_errado&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api, true",
            "http://books.google.com/books/content?id=r5mdDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api, true"
    })
    @DirtiesContext
    @DisplayName("Should be able validating url image")
    public void shouldBeableValidatingUrlImage(String url, boolean isSuccess) {
        MangaRequest mangaRequest = getMangaRequest(url);

        ResponseEntity<String> response = testRestTemplate.exchange("/catalog/manga/", HttpMethod.POST, this.getEntity(mangaRequest), String.class);

        System.err.println(response.getStatusCode().is2xxSuccessful());
        System.err.println(isSuccess);
        assertThat(response.getStatusCode().is2xxSuccessful()).isEqualTo(isSuccess);
        assertThat(response.getStatusCode()).isNotEqualTo(HttpStatus.UNAUTHORIZED);
    }

    private @NotNull HttpEntity getEntity() {
        HttpHeaders headers = this.getHttpHeaders();
        return new HttpEntity(headers);
    }

    private @NotNull HttpEntity getEntity(MangaRequest mangaRequest) {
        HttpHeaders headers = this.getHttpHeaders();
        return new HttpEntity(mangaRequest, headers);
    }

    private @NotNull HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TokenProviderIT.getAccessToken());
        return headers;
    }
}

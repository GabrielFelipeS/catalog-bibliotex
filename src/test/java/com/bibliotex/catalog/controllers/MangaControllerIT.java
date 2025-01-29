package com.bibliotex.catalog.controllers;

import com.bibliotex.catalog.TokenProviderIT;
import com.bibliotex.catalog.customsAsserts.CatalogAssert;
import com.bibliotex.catalog.domain.dto.request.MangaRequest;
import com.bibliotex.catalog.domain.model.Book;
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
                "Naruto",
                "Naruto Uzumaki é um jovem ninja com o sonho de se tornar o Hokage, o líder de sua vila.",
                700,
                "Japonês",
                1,
                1999,
                authorsIds,
                publisherId,
                imgUrl,
                "Edição especial",
                true
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

        MangaRequest mangaRequest = new MangaRequest("Naruto",
                "Naruto Uzumaki é um jovem ninja com o sonho de se tornar o Hokage, o líder de sua vila.",
                700,
                "Japonês",
                1,
                1999,
                List.of(1L, 2L),
                123L,
                "http://books.google.com/books/content?id=THsEEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
                "Edição especial",
                true);

        ResponseEntity<String> response = testRestTemplate.exchange("/catalog/manga/", HttpMethod.POST, this.getEntity(mangaRequest), String.class);
        System.err.println(response.getBody());
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
                0,
                "",
                0,
                0,
                authorsIds,
                publisherId,
                "",
                "",
                null
        );

        ResponseEntity<String> response = testRestTemplate.exchange("/catalog/manga/", HttpMethod.POST, this.getEntity(mangaRequest), String.class);

        System.err.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int fieldsError = documentContext.read("$.length()");

        assertThat(fieldsError).isEqualTo(12);

        List<String> errorsTitle = documentContext.read("$.title");
        List<String> errorsDescription = documentContext.read("$.description");
        List<String> errorsPages = documentContext.read("$.pages");
        List<String> errorsLanguage = documentContext.read("$.language");
        List<String> errorsEdition = documentContext.read("$.edition");
        List<String> errorsYearOfRelease = documentContext.read("$.yearOfRelease");
        List<String> errorsPublisherId = documentContext.read("$.publisherId");
        List<String> errorsImageUrl = documentContext.read("$.imageUrl");
        List<String> errorsIsOnGoing = documentContext.read("$.isOngoing");
        List<String> errorsMagazine = documentContext.read("$.magazine");
        List<String> errorsAuthorsIds1 = documentContext.read("$.['authorsIds[1]']");
        List<String> errorsAuthorsIds2 = documentContext.read("$.['authorsIds[2]']");

        assertThat(errorsTitle).containsExactlyInAnyOrder("O titulo não deveria ser nulo ou vazio");
        assertThat(errorsDescription).containsExactlyInAnyOrder("A descrição não deveria ser nula ou vazia");
        assertThat(errorsPages).containsExactlyInAnyOrder("A quantidade de páginas deve ser maior que zero");
        assertThat(errorsLanguage).containsExactlyInAnyOrder("A lingua não deveria ser nula ou vazia");
        assertThat(errorsEdition).containsExactlyInAnyOrder("A edição deve ser maior que zero");
        assertThat(errorsYearOfRelease).containsExactlyInAnyOrder("A quantidade de páginas deve ser maior que zero");
        assertThat(errorsPublisherId).containsExactlyInAnyOrder("A quantidade de páginas deve ser maior que zero");
        assertThat(errorsImageUrl).containsExactlyInAnyOrder("A imageUrl não deveria ser nulo ou vazio", "O endereço da imagem não é valido, por favor insira outro");
        assertThat(errorsAuthorsIds1).containsExactlyInAnyOrder("O id do autor deve ser maior que zero");
        assertThat(errorsAuthorsIds2).containsExactlyInAnyOrder("O id do autor deve ser maior que zero");
        assertThat(errorsIsOnGoing).containsExactlyInAnyOrder("isOngoing não deveria ser nulo");
        assertThat(errorsMagazine).containsExactlyInAnyOrder("A magazine não deveria ser nulo ou vazio");
    }

    @Test
    @DirtiesContext
    @DisplayName("Should be not able create new book with valid book values")
    void shouldBeNotAbleCreateBookWhenAuthorNotExists() {
        List<Long> authorsIds = List.of(1L, 100L);
        Long publisherId = 1L;

        MangaRequest mangaRequest = new MangaRequest(
                "Naruto",
                "Naruto Uzumaki é um jovem ninja com o sonho de se tornar o Hokage, o líder de sua vila.",
                700,
                "Japonês",
                1,
                1999,
                authorsIds,
                publisherId,
                "http://books.google.com/books/content?id=THsEEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
                "Edição especial",
                true
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
                700,
                "Japonês",
                1,
                1999,
                authorsIds,
                publisherId,
                "http://books.google.com/books/content?id=THsEEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
                "Edição especial",
                true
        );

        ResponseEntity<String> response = testRestTemplate.exchange("/catalog/manga/", HttpMethod.POST, this.getEntity(mangaRequest), String.class);
        System.err.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    @DisplayName("Should return book when id exists")
    public void findBookWhenIdExists() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/catalog/manga/1", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Book book = documentContext.read("$.data", Book.class);

        CatalogAssert.assertThat(book)
                .idNotNull()
                .idIsPositive()
                .hasTitle("Harry Potter and the Philosopher's Stone")
                .hasDescription("First book of the Harry Potter series")
                .hasPages(223)
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

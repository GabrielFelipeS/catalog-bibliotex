package com.bibliotex.catalog.controllers;

import com.bibliotex.catalog.TokenProviderIT;
import com.bibliotex.catalog.customsAsserts.BookAssert;
import com.bibliotex.catalog.customsAsserts.CatalogAssert;
import com.bibliotex.catalog.domain.dto.request.BookRequest;
import com.bibliotex.catalog.domain.model.Book;
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
class BookControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private KafkaService kafkaService;

    private static @NotNull BookRequest getBookRequest(String imgUrl) {
        List<Long> authorsIds = Arrays.asList(1L, 2L);
        Long publisherId = 1L;
        BookRequest bookRequest = new BookRequest(
                "Título do Livro",
                "Descrição detalhada do livro.",
                350,
                "Português",
                2,
                2025,
                authorsIds,
                publisherId,
                imgUrl,
                "978-3-16-148410-0"
        );
        return bookRequest;
    }

    @Test
    @DirtiesContext
    @DisplayName("Should be able create new book with valid book values")
    void shouldBeAbleCreateBook() {
        BookRequest bookRequest = getBookRequest("http://books.google.com/books/content?id=r5mdDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api");

        ResponseEntity<String> response = testRestTemplate.exchange("/catalog/books/", HttpMethod.POST, this.getEntity(bookRequest), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        String location = Objects.requireNonNull(response.getHeaders().getLocation()).getPath();

        response = testRestTemplate.getForEntity(location, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Book book = documentContext.read("$.data", Book.class);

        CatalogAssert.assertThat(book)
                .idNotNull()
                .isValid(bookRequest)
        ;

        BookAssert.assertThat(book)
                .hasIsbnNoSpecialCharacter(bookRequest.isbn())
        ;
    }

    @Test
    @DirtiesContext
    @DisplayName("Should be not able create new book with valid book values")
    void shouldBeNotAbleCreateBookWhenPublisherNotExists() {
        List<Long> authorsIds = List.of(1L);
        Long publisherId = 100L;

        BookRequest bookRequest = new BookRequest(
                "Titulo de teste",
                "First book of the Harry Potter series",
                223,
                "English",
                1,
                1997,
                authorsIds,
                publisherId,
                "http://books.google.com/books/content?id=r5mdDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
                "9780747532691"

        );

        ResponseEntity<String> response = testRestTemplate.exchange("/catalog/books/", HttpMethod.POST, this.getEntity(bookRequest), String.class);
        System.err.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext
    @DisplayName("Should be not able create new book with valid book values")
    void shouldBeNotAbleCreateBookWhenValuesIncorrect() {
        List<Long> authorsIds = List.of(1L, 0L, -1L);
        Long publisherId = -1L;

        BookRequest bookRequest = new BookRequest(
                "",
                "",
                0,
                "",
                0,
                0,
                authorsIds,
                publisherId,
                "",
                ""
        );

        ResponseEntity<String> response = testRestTemplate.exchange("/catalog/books/", HttpMethod.POST, this.getEntity(bookRequest), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        System.err.println(response.getBody());

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int fieldsError = documentContext.read("$.length()");

        assertThat(fieldsError).isEqualTo(11);

        List<String> errorsTitle = documentContext.read("$.title");
        List<String> errorsDescription = documentContext.read("$.description");
        List<String> errorsPages = documentContext.read("$.pages");
        List<String> errorsLanguage = documentContext.read("$.language");
        List<String> errorsEdition = documentContext.read("$.edition");
        List<String> errorsYearOfRelease = documentContext.read("$.yearOfRelease");
        List<String> errorsPublisherId = documentContext.read("$.publisherId");
        List<String> errorsIsbn = documentContext.read("$.isbn");
        List<String> errorsImageUrl = documentContext.read("$.imageUrl");
        List<String> errorsAuthorsIds1 = documentContext.read("$.['authorsIds[1]']");
        List<String> errorsAuthorsIds2 = documentContext.read("$.['authorsIds[2]']");

        assertThat(errorsTitle).containsExactlyInAnyOrder("O titulo não deveria ser nulo ou vazio");
        assertThat(errorsDescription).containsExactlyInAnyOrder("A descrição não deveria ser nula ou vazia");
        assertThat(errorsPages).containsExactlyInAnyOrder("A quantidade de páginas deve ser maior que zero");
        assertThat(errorsLanguage).containsExactlyInAnyOrder("A lingua não deveria ser nula ou vazia");
        assertThat(errorsEdition).containsExactlyInAnyOrder("A edição deve ser maior que zero");
        assertThat(errorsYearOfRelease).containsExactlyInAnyOrder("A quantidade de páginas deve ser maior que zero");
        assertThat(errorsPublisherId).containsExactlyInAnyOrder("A quantidade de páginas deve ser maior que zero");
        assertThat(errorsIsbn).containsExactlyInAnyOrder("O ISBN não deveria ser nulo ou vazio");
        assertThat(errorsImageUrl).containsExactlyInAnyOrder("A imageUrl não deveria ser nulo ou vazio", "O endereço da imagem não é valido, por favor insira outro");
        assertThat(errorsAuthorsIds1).containsExactlyInAnyOrder("O id do autor deve ser maior que zero");
        assertThat(errorsAuthorsIds2).containsExactlyInAnyOrder("O id do autor deve ser maior que zero");
    }

    @Test
    @DirtiesContext
    @DisplayName("Should be not able create new book with valid book values")
    void shouldBeNotAbleCreateBookWhenAuthorNotExists() {
        List<Long> authorsIds = List.of(1L, 100L);
        Long publisherId = 1L;

        BookRequest bookRequest = new BookRequest(
                "Titulo teste",
                "Should be not able create new book with valid book values",
                223,
                "English",
                1,
                1997,
                authorsIds,
                publisherId,
                "http://books.google.com/books/content?id=r5mdDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
                "9780747532691"
        );

        ResponseEntity<String> response = testRestTemplate.exchange("/catalog/books/", HttpMethod.POST, this.getEntity(bookRequest), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext
    @DisplayName("Should be not able create new book with valid book values")
    void shouldBeNotAbleCreateBook() {
        List<Long> authorsIds = List.of(1L);
        Long publisherId = 1L;

        BookRequest bookRequest = new BookRequest(
                "Harry Potter and the Philosopher's Stone",
                "First book of the Harry Potter series",
                223,
                "English",
                1,
                1997,
                authorsIds,
                publisherId,
                "http://books.google.com/books/content?id=r5mdDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
                "9780747532699"
        );

        ResponseEntity<String> response = testRestTemplate.exchange("/catalog/books/", HttpMethod.POST, this.getEntity(bookRequest), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    @DisplayName("Should return book when id exists")
    public void findBookWhenIdExists() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/catalog/books/1", String.class);

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
        ResponseEntity<String> response = testRestTemplate.getForEntity("/catalog/books/100", String.class);
        System.err.println(response.getStatusCode());
        System.err.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Should return all books in the catalog")
    void getBooks() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/catalog/books/", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());

        List<Book> books = documentContext.read("$.data");

        assertThat(books).isNotEmpty();
    }

    @Test
    @DirtiesContext
    @DisplayName("Should delete book when id exists")
    public void deleteBook() {
        ResponseEntity<String> response = testRestTemplate.exchange("/catalog/books/1", HttpMethod.DELETE, this.getEntity(), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        response = testRestTemplate.getForEntity("/catalog/books/1", String.class);

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
        BookRequest bookRequest = getBookRequest(url);

        ResponseEntity<String> response = testRestTemplate.exchange("/catalog/books/", HttpMethod.POST, this.getEntity(bookRequest), String.class);
        System.err.println(response.getBody());
        System.err.println(response.getStatusCode().is2xxSuccessful());
        System.err.println(isSuccess);
        assertThat(response.getStatusCode().is2xxSuccessful()).isEqualTo(isSuccess);
        assertThat(response.getStatusCode()).isNotEqualTo(HttpStatus.UNAUTHORIZED);
    }

    private @NotNull HttpEntity getEntity() {
        HttpHeaders headers = this.getHttpHeaders();
        return new HttpEntity(headers);
    }

    private @NotNull HttpEntity getEntity(BookRequest bookRequest) {
        HttpHeaders headers = this.getHttpHeaders();
        return new HttpEntity(bookRequest, headers);
    }

    private @NotNull HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TokenProviderIT.getAccessToken());
        return headers;
    }
}

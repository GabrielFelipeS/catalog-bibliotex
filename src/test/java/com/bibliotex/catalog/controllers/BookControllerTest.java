package com.bibliotex.catalog.controllers;

import com.bibliotex.catalog.domain.model.Book;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getBooks() {
       ResponseEntity<String> response = testRestTemplate.getForEntity("/books", String.class);

       assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());

        List<Book> books =  documentContext.read("$.data");

        assertThat(books).isNotEmpty();
    }
}
package com.bibliotex.catalog.controllers;

import com.bibliotex.catalog.domain.dto.request.BookRequest;
import com.bibliotex.catalog.domain.dto.response.BookResponse;
import com.bibliotex.catalog.services.BookService;
import com.bibliotex.catalog.services.KafkaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/catalog/books/")
public class BookController {
    private final KafkaService kafkaService;
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody @Valid BookRequest bookRequest, UriComponentsBuilder uriBuilder) {
        BookResponse bookResponse = bookService.create(bookRequest);

        kafkaService.sendMessageCreate(bookResponse);

        URI location = uriBuilder.path("/catalog/books/{id}")
                .buildAndExpand(bookResponse.id())
                .toUri();

        return ResponseEntity.created(location).body(bookResponse);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> findAll() {
        List<BookResponse> booksResponses = bookService.findAll();

        return ResponseEntity.ok(booksResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable Long id) {
        BookResponse bookResponse = bookService.findBy(id);

        return ResponseEntity.ok(bookResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        bookService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

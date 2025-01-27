package com.bibliotex.catalog.controllers;

import com.bibliotex.catalog.domain.dto.request.BookRequest;
import com.bibliotex.catalog.domain.dto.response.ApiResponse;
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
@RequestMapping("/books/")
public class BookController {
    private final KafkaService kafkaService;
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse> createBook(@RequestBody @Valid BookRequest bookRequest, UriComponentsBuilder uriBuilder) {
        BookResponse bookResponse = bookService.createBook(bookRequest);

        kafkaService.sendMessageCreate(bookResponse);

        URI location = uriBuilder.path("/books/{id}")
                .buildAndExpand(bookResponse.id())
                .toUri();

        return ResponseEntity.created(location).body(new ApiResponse("Livro criado com sucesso!", bookResponse));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAllBooks() {
        List<BookResponse> booksResponses = bookService.findAll();

        return ResponseEntity.ok(new ApiResponse("Livros encontrados com sucesso!", booksResponses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findBookById(@PathVariable Long id) {
        BookResponse bookResponse = bookService.findBy(id);

        return ResponseEntity.ok(new ApiResponse("Livros encontrados com sucesso!", bookResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
        bookService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

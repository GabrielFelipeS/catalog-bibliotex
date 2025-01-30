package com.bibliotex.catalog.controllers;

import com.bibliotex.catalog.domain.dto.request.ComicRequest;
import com.bibliotex.catalog.domain.dto.response.ApiResponse;
import com.bibliotex.catalog.domain.dto.response.ComicResponse;
import com.bibliotex.catalog.services.ComicService;
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
@RequestMapping("/catalog/comic/")
public class ComicController {
    private final ComicService comicService;
    private final KafkaService kafkaService;

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid ComicRequest comicRequest, UriComponentsBuilder uriBuilder) {
        ComicResponse comicResponse = comicService.create(comicRequest);

        kafkaService.sendMessageCreate(comicResponse);

        URI location = uriBuilder.path("/catalog/comic/{id}")
                .buildAndExpand(comicResponse.id())
                .toUri();

        return ResponseEntity.created(location).body(new ApiResponse("Quadrinho criado com sucesso!", comicResponse));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAll() {
        List<ComicResponse> comicResponse = comicService.findAll();

        return ResponseEntity.ok(new ApiResponse("Quadrinhos encontrados com sucesso!", comicResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id) {
        ComicResponse comicResponse = comicService.findBy(id);

        return ResponseEntity.ok(new ApiResponse("Quadrinho encontrado com sucesso!", comicResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        comicService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

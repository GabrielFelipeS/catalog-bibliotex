package com.bibliotex.catalog.controllers;

import com.bibliotex.catalog.domain.dto.request.MangaRequest;
import com.bibliotex.catalog.domain.dto.response.MangaResponse;
import com.bibliotex.catalog.services.KafkaService;
import com.bibliotex.catalog.services.MangaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/catalog/manga/")
public class MangaController {
    private final KafkaService kafkaService;
    private final MangaService mangaService;

    @PostMapping
    public ResponseEntity<MangaResponse> create(@RequestBody @Valid MangaRequest mangaRequest, UriComponentsBuilder uriBuilder) {
        MangaResponse mangaResponse = mangaService.create(mangaRequest);

        kafkaService.sendMessageCreate(mangaResponse);

        URI location = uriBuilder.path("/catalog/manga/{id}")
                .buildAndExpand(mangaResponse.id())
                .toUri();

        return ResponseEntity.created(location).body(mangaResponse);
    }

    @GetMapping
    public ResponseEntity<List<MangaResponse>> findAll() {
        List<MangaResponse> mangaResponse = mangaService.findAll();

        return ResponseEntity.ok(mangaResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MangaResponse> findById(@PathVariable Long id) {
        MangaResponse mangaResponse = mangaService.findBy(id);

        return ResponseEntity.ok(mangaResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        mangaService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

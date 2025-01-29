package com.bibliotex.catalog.controllers;

import com.bibliotex.catalog.domain.dto.request.MangaRequest;
import com.bibliotex.catalog.domain.dto.response.ApiResponse;
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
    public ResponseEntity<ApiResponse> createManga(@RequestBody @Valid MangaRequest mangaRequest, UriComponentsBuilder uriBuilder) {
        MangaResponse mangaResponse = mangaService.create(mangaRequest);

        kafkaService.sendMessageCreate(mangaResponse);

        URI location = uriBuilder.path("/catalog/manga/{id}")
                .buildAndExpand(mangaResponse.id())
                .toUri();

        return ResponseEntity.created(location).body(new ApiResponse("Manga criado com sucesso!", mangaResponse));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAllMangas() {
        List<MangaResponse> mangaResponse = mangaService.findAll();

        return ResponseEntity.ok(new ApiResponse("Mangas encontrados com sucesso!", mangaResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findMangaById(@PathVariable Long id) {
        MangaResponse mangaResponse = mangaService.findBy(id);

        return ResponseEntity.ok(new ApiResponse("Manga encontrado com sucesso!", mangaResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMangayId(@PathVariable Long id) {
        mangaService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

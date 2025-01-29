package com.bibliotex.catalog.services;

import com.bibliotex.catalog.domain.dto.request.MangaRequest;
import com.bibliotex.catalog.domain.dto.response.MangaResponse;
import com.bibliotex.catalog.domain.model.Manga;
import com.bibliotex.catalog.exceptions.BookNotFoundException;
import com.bibliotex.catalog.mappers.MangaMapper;
import com.bibliotex.catalog.repositories.MangaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class MangaService {
    private final MangaRepository mangaRepository;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final ImageValidationService imageValidationService;
    private final MangaMapper mangaMapper;

    public MangaResponse create(MangaRequest mangaRequest) {
        boolean isInvalidUrlImage = imageValidationService.isInvalid(mangaRequest.imageUrl());

        if (isInvalidUrlImage) throw new IllegalStateException("A imagem fornecida não existe");
        System.err.println("passou 1");
//        boolean isbnAlreadyExists = mangaRepository.existsBookByIsbn(mangaRequest.isbn());
//
//        if (isbnAlreadyExists) throw new IllegalStateException("Isbn já existe");

        var authors = authorService.findById(mangaRequest.authorsIds());
        var publisher = publisherService.findById(mangaRequest.publisherId());

        System.err.println("passou 2");
        Manga manga = mangaMapper.toEntity(mangaRequest, authors, publisher);

        manga = mangaRepository.save(manga);

        return mangaMapper.toDTO(manga);
    }

    public List<MangaResponse> findAll() {
        return mangaRepository.findAll().stream().map(mangaMapper::toDTO).toList();
    }

    public MangaResponse findBy(Long id) {
        Manga manga = mangaRepository.findById(id).orElseThrow(BookNotFoundException::new);

        return mangaMapper.toDTO(manga);
    }

    public void deleteById(Long id) {
        mangaRepository.deleteById(id);
    }
}

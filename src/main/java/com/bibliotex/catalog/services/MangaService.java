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
    private final MangaMapper mangaMapper;
    private final ValidatorService validatorService;
    private final GenreService genreService;

    public MangaResponse create(MangaRequest mangaRequest) {
        validatorService.isValidOrThrow(mangaRequest);

        var authors = authorService.findById(mangaRequest.authorsIds());
        var genres = genreService.findById(mangaRequest.genresIds());
        var publisher = publisherService.findById(mangaRequest.publisherId());

        System.err.println("publisher: " + publisher);
        System.err.println("authors: " + authors);

        Manga manga = mangaMapper.toEntity(mangaRequest, authors, publisher, genres);

        System.err.println("manga: " + manga);
        System.err.println("publisher: " + manga.getPublisher());
        System.err.println("authors: " + manga.getAuthors());

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

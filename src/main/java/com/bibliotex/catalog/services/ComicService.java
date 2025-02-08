package com.bibliotex.catalog.services;

import com.bibliotex.catalog.domain.dto.request.ComicRequest;
import com.bibliotex.catalog.domain.dto.response.ComicResponse;
import com.bibliotex.catalog.domain.model.Comic;
import com.bibliotex.catalog.exceptions.ComicNotFoundException;
import com.bibliotex.catalog.mappers.ComicMapper;
import com.bibliotex.catalog.repositories.ComicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ComicService {
    private final ComicRepository comicRepository;
    private final ComicMapper comicMapper;
    private final ValidatorService validatorService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final GenreService genreService;

    public ComicResponse create(ComicRequest comicRequest) {
        validatorService.isValidOrThrow(comicRequest);

        var authors = authorService.findById(comicRequest.authorsIds());
        var genres = genreService.findById(comicRequest.genresIds());
        var publisher = publisherService.findById(comicRequest.publisherId());

        Comic comic = comicMapper.toEntity(comicRequest, authors, publisher, genres);

        comic = comicRepository.save(comic);

        return comicMapper.toDTO(comic);
    }

    public List<ComicResponse> findAll() {
        return this.comicRepository.findAll().stream().map(comicMapper::toDTO).toList();
    }

    public ComicResponse findBy(Long id) {
        Comic comic = comicRepository.findById(id).orElseThrow(ComicNotFoundException::new);

        return comicMapper.toDTO(comic);
    }

    public void deleteById(Long id) {
        comicRepository.deleteById(id);
    }
}

package com.bibliotex.catalog.services;

import com.bibliotex.catalog.domain.model.Genre;
import com.bibliotex.catalog.exceptions.GenreNotFoundException;
import com.bibliotex.catalog.repositories.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    /**
     * Return {@linkplain Genre} by id or throw {@linkplain NoSuchElementException} when genre with id does not exist
     *
     * @param id id of the genre
     * @return Genre
     * @throws NoSuchElementException When genre with id specified does not exist
     */
    public Genre findById(Long id) {
        return genreRepository.findById(id).orElseThrow(GenreNotFoundException::new);
    }

    /**
     * Return {@linkplain Genre} by id or throw {@linkplain NoSuchElementException} when genre with id does not exist
     * s
     *
     * @param genresIds list of the genre's ids
     * @return Genre
     * @throws NoSuchElementException When genre with id does not exist
     */
    public List<Genre> findById(List<Long> genresIds) {
        return genresIds.stream().map(this::findById).toList();
    }
}

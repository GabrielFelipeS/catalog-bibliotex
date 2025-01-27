package com.bibliotex.catalog.services;

import com.bibliotex.catalog.domain.model.Author;
import com.bibliotex.catalog.exceptions.AuthorNotFoundException;
import com.bibliotex.catalog.repositories.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    /**
     * Return {@linkplain Author} by id or throw {@linkplain NoSuchElementException} when author with id does not exist
     *
     * @param id id of the publisher
     * @return Author
     * @throws NoSuchElementException When publisher with id does not exist
     */
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
    }

    /**
     * Return {@linkplain Author} by id or throw {@linkplain NoSuchElementException} when author with id does not exist
     * s
     *
     * @param authorsIds list of the author's ids
     * @return Author
     * @throws NoSuchElementException When publisher with id does not exist
     */
    public List<Author> findById(List<Long> authorsIds) {
        return authorsIds.stream().map(this::findById).toList();
    }
}

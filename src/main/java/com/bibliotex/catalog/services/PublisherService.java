package com.bibliotex.catalog.services;

import com.bibliotex.catalog.domain.model.Publisher;
import com.bibliotex.catalog.exceptions.PublisherNotFoundException;
import com.bibliotex.catalog.repositories.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;

    /**
     * Return {@linkplain Publisher} by id or throw {@linkplain NoSuchElementException} when publisher with id does not exist
     *
     * @param id
     * @return Publisher
     * @throws NoSuchElementException When publisher with id does not exist
     */
    public Publisher findById(Long id) {
        return publisherRepository.findById(id).orElseThrow(PublisherNotFoundException::new);
    }
}

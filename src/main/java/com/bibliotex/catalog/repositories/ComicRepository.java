package com.bibliotex.catalog.repositories;

import com.bibliotex.catalog.domain.model.Comic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ComicRepository extends CrudRepository<Comic, Long> {
    List<Comic> findAll();

    Optional<Comic> findByIdAndIsActiveTrue(Long id);
}

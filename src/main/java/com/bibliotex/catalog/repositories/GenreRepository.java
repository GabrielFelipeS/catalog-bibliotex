package com.bibliotex.catalog.repositories;


import com.bibliotex.catalog.domain.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}

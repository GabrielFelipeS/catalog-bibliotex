package com.bibliotex.catalog.repositories;

import com.bibliotex.catalog.domain.model.Manga;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MangaRepository extends CrudRepository<Manga, Long> {
    List<Manga> findAll();
}


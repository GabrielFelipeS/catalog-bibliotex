package com.bibliotex.catalog.repositories;

import com.bibliotex.catalog.domain.model.Manga;
import org.springframework.data.repository.CrudRepository;

public interface MangaRepository extends CrudRepository<Manga, Long> {
}

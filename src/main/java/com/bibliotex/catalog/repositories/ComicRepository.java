package com.bibliotex.catalog.repositories;

import com.bibliotex.catalog.domain.model.Comic;
import org.springframework.data.repository.CrudRepository;

public interface ComicRepository extends CrudRepository<Comic, Long> {
}

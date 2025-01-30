package com.bibliotex.catalog.repositories;

import com.bibliotex.catalog.domain.model.Comic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComicRepository extends CrudRepository<Comic, Long> {
    List<Comic> findAll();
}

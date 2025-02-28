package com.bibliotex.catalog.repositories;

import com.bibliotex.catalog.domain.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}

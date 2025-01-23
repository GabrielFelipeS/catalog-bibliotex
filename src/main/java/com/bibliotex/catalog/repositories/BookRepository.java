package com.bibliotex.catalog.repositories;

import com.bibliotex.catalog.domain.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}

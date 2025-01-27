package com.bibliotex.catalog.repositories;

import com.bibliotex.catalog.domain.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAll();

    boolean existsBookByIsbn(String isbn);
}

package com.bibliotex.catalog.repositories;

import com.bibliotex.catalog.domain.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAll();

    Optional<Book> findByIdAndIsActiveTrue(Long id);
    boolean existsBookByIsbn(String isbn);
}

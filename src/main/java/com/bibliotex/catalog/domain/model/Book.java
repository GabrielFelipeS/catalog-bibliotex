package com.bibliotex.catalog.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book extends Catalog{
    private String isbn;

    public Book(String title, String description, Integer pages, String language,
                Integer edtiion, Integer yearOfRelease, String imageUrl, List<Author> authors, Publisher publisher, String isbn) {
        super(title, description, pages, language, edtiion, yearOfRelease, imageUrl, authors, publisher);
        this.isbn = isbn;
    }
}

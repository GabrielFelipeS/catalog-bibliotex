package com.bibliotex.catalog.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book extends Catalog{
    private String isbn;

    @ManyToMany
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Book(String title, Integer pages, String languague,
                Integer edtiion, Integer yearOfRelease, List<Author> authors, Publisher publisher, String isbn) {
        super(title, pages, languague, edtiion, yearOfRelease);
        this.authors = authors;
        this.publisher = publisher;
        this.isbn = isbn;
    }
}

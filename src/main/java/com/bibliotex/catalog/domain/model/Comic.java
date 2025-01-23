package com.bibliotex.catalog.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comic extends Catalog{
    private String universe;

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

    public Comic(String title, String description,Integer pages, String languague,
                Integer edtiion, Integer yearOfRelease, String imageUrl, List<Author> authors, Publisher publisher, String universe) {
        super(title, description, pages, languague, edtiion, yearOfRelease, imageUrl, authors, publisher);
        this.universe = universe;
    }
}

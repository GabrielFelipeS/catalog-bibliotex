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
public class Manga extends Catalog{
    private Boolean isOngoing;
    private String magazine;

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

    public Manga(String title, String description, Integer pages, String language,
                Integer edtiion, Integer yearOfRelease, String imageUrl, List<Author> authors, Publisher publisher, Boolean isOngoing, String magazine) {
        super(title, description, pages, language, edtiion, yearOfRelease, imageUrl, authors, publisher);
        this.isOngoing = isOngoing;
        this.magazine = magazine;
    }
}

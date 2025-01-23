package com.bibliotex.catalog.domain.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Catalog extends BaseEntity {
    private String title;
    private String description;
    private Integer pages;
    private String language;
    private Integer edition;
    private Integer yearOfRelease;
    private String imageUrl;

    @ManyToMany
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "catalog_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Catalog(String title, String description, Integer pages, String language, Integer edition, Integer yearOfRelease, String imageUrl, List<Author> authors, Publisher publisher) {
        this.title = title;
        this.description = description;
        this.pages = pages;
        this.language = language;
        this.edition = edition;
        this.yearOfRelease = yearOfRelease;
        this.imageUrl = imageUrl;
        this.authors = authors;
        this.publisher = publisher;
    }
}

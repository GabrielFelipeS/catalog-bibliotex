package com.bibliotex.catalog.domain.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Manga extends Catalog{
    private Boolean isOnGoing;
    private String magazine;

    public Manga(String title, String description, Integer pages, String language,
                 Integer edtiion, Integer yearOfRelease, String imageUrl, List<Author> authors, Publisher publisher, Boolean isOnGoing, String magazine) {
        super(title, description, pages, language, edtiion, yearOfRelease, imageUrl, authors, publisher);
        this.isOnGoing = isOnGoing;
        this.magazine = magazine;
    }
}

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
public class Comic extends Catalog{
    private String universe;

    public Comic(String title, String description,Integer pages, String language,
                 Integer edition, Integer yearOfRelease, String imageUrl, List<Author> authors, Publisher publisher, String universe) {
        super(title, description, pages, language, edition, yearOfRelease, imageUrl, authors, publisher);
        this.universe = universe;
    }
}

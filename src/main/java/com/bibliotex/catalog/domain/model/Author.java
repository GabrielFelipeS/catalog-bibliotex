package com.bibliotex.catalog.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Author extends BaseEntity {
    private String name;
    private String nationality;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

    @ManyToMany(mappedBy = "authors")
    private List<Comic> comics = new ArrayList<>();

    @ManyToMany(mappedBy = "authors")
    private List<Manga> mangas = new ArrayList<>();
}

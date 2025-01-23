package com.bibliotex.catalog.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Publisher extends BaseEntity {
    private String name;
    private String country;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books = new ArrayList<>();

    @OneToMany(mappedBy = "publisher")
    private List<Comic> comics = new ArrayList<>();

    @OneToMany(mappedBy = "publisher")
    private List<Manga> mangas = new ArrayList<>();
}

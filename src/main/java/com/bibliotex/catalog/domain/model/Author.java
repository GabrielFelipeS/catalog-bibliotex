package com.bibliotex.catalog.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
    private List<Catalog> catalogs = new ArrayList<>();
}

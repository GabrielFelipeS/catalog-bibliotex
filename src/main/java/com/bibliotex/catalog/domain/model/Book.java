package com.bibliotex.catalog.domain.model;

import com.bibliotex.catalog.domain.enums.ContentRating;
import com.bibliotex.catalog.domain.enums.PublicationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @Column(nullable = false, unique = true)
    private String isbn;

    public Book(String title, String description, Integer pages,
                String language, Integer edition, Integer yearOfRelease,
                String imageUrl, String isbn, List<Author> authors,
                Publisher publisher, Integer numReaders, Boolean hasAdaptation, PublicationStatus publicationStatus,
                ContentRating classification, List<Genre> genres, Boolean isActive) {
        super(title, description, pages, language, edition, yearOfRelease, imageUrl, authors, publisher, numReaders, hasAdaptation, publicationStatus, classification, genres, isActive);
        this.isbn = isbn;
    }
}

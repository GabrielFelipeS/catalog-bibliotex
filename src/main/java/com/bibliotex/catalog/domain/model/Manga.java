package com.bibliotex.catalog.domain.model;

import com.bibliotex.catalog.domain.enums.ContentRating;
import com.bibliotex.catalog.domain.enums.PublicationStatus;
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
    private String originalTitle;

//    private List<String> demographics;

//    Adição posterior
//     private int volumes;
//    private int chapters;



    public Manga(String title, String description, Integer pages, String language,
                 Integer edition, Integer yearOfRelease, String imageUrl,
                 List<Author> authors, Publisher publisher, String originalTitle, Integer numReaders,
                 Boolean hasAdaptation, PublicationStatus publicationStatus,
                 ContentRating classification, List<Genre> genres, Boolean isActive) {
        super(title, description, pages, language, edition, yearOfRelease, imageUrl, authors, publisher, numReaders, hasAdaptation, publicationStatus, classification, genres, isActive);
        this.originalTitle = originalTitle;
    }
}

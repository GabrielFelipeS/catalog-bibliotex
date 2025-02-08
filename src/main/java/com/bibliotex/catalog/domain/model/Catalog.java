package com.bibliotex.catalog.domain.model;

import com.bibliotex.catalog.domain.enums.ContentRating;
import com.bibliotex.catalog.domain.enums.PublicationStatus;
import com.bibliotex.catalog.domain.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(
        name = "catalog",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"title", "edition"})
        }
)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Catalog extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private Integer edition;

    @Column(nullable = false)
    private Integer yearOfRelease;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Integer numReaders;

    @Column(nullable = false)
    private Boolean hasAdaptation;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PublicationStatus publicationStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ContentRating classification;

    @ManyToMany
    @JoinTable(
            name = "genre_catalog",
            joinColumns = @JoinColumn(name = "catalog_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "catalog_authors",
            joinColumns = @JoinColumn(name = "catalog_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(nullable = false)
    private Boolean isActive;

    public Catalog(String title, String description, Integer pages,
                   String language, Integer edition, Integer yearOfRelease,
                   String imageUrl, List<Author> authors, Publisher publisher, Integer numReaders, Boolean hasAdaptation, PublicationStatus publicationStatus, ContentRating classification, List<Genre> genres, Boolean isActive) {
        this.title = title;
        this.description = description;
        this.pages = pages;
        this.language = language;
        this.edition = edition;
        this.yearOfRelease = yearOfRelease;
        this.imageUrl = imageUrl;
        this.authors = authors;
        this.publisher = publisher;
        this.numReaders = numReaders;
        this.hasAdaptation = hasAdaptation;
        this.publicationStatus = publicationStatus;
        this.classification = classification;
        this.genres = genres;
        this.isActive = isActive;
    }

    @JsonIgnore
    public List<Long> getAuthorsId() {
        return authors.stream().map(Author::getId).toList();
    }

    @JsonIgnore
    public Long getPublisherId() {
        return publisher.getId();
    }
}

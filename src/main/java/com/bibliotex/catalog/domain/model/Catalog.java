package com.bibliotex.catalog.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class Catalog extends BaseEntity {
    private String title;
    private Integer pages;
    private String language;
    private Integer edition;
    private Integer yearOfRelease;

    public Catalog(String title, Integer pages, String language, Integer edition, Integer yearOfRelease) {
        this.title = title;
        this.pages = pages;
        this.language = language;
        this.edition = edition;
        this.yearOfRelease = yearOfRelease;
    }
}

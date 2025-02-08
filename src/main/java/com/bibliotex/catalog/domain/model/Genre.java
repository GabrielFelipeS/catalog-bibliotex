package com.bibliotex.catalog.domain.model;

import com.bibliotex.catalog.domain.model.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Genre extends BaseEntity {
    private String name;
}

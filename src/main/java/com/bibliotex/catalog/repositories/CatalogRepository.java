package com.bibliotex.catalog.repositories;

import com.bibliotex.catalog.domain.model.Catalog;
import org.springframework.data.repository.CrudRepository;

public interface CatalogRepository extends CrudRepository<Catalog, Long> {
}

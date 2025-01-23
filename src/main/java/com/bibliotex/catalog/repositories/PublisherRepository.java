package com.bibliotex.catalog.repositories;

import com.bibliotex.catalog.domain.model.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}

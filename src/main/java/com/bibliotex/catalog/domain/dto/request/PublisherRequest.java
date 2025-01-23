package com.bibliotex.catalog.domain.dto.request;


import com.bibliotex.catalog.domain.model.Catalog;
import java.util.List;


public record PublisherRequest (
    String name,
    String country,
    List<Catalog> catalogs
) { }

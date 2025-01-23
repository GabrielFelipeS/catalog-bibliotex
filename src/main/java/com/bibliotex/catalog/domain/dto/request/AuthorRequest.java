package com.bibliotex.catalog.domain.dto.request;

import com.bibliotex.catalog.domain.model.Catalog;
import java.util.List;


public record AuthorRequest (
    String name,
    String nationality,
    List<Catalog> catalogs
){}

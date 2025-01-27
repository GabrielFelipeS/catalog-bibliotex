package com.bibliotex.catalog.domain.dto.response;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        HttpStatus status,
        String message
) {
}

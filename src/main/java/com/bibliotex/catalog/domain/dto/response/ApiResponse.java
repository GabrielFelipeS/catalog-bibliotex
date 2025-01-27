package com.bibliotex.catalog.domain.dto.response;

public record ApiResponse(
        String message,
        Object data
) {
}

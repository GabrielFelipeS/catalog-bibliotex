package com.bibliotex.catalog.services;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImageValidationService {
    private final RestTemplate restTemplate;

    public ImageValidationService() {
        this.restTemplate = new RestTemplate();
    }

    public boolean isInvalid(String url) {
        return !isValid(url);
    }

    public boolean isValid(String url) {
        if (url == null || url.isEmpty()) {
            return false;
        }

        try {
            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.HEAD, null, Void.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}

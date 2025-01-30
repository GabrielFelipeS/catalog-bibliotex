package com.bibliotex.catalog.services;

import com.bibliotex.catalog.domain.dto.request.BookRequest;
import com.bibliotex.catalog.domain.dto.request.ComicRequest;
import com.bibliotex.catalog.domain.dto.request.MangaRequest;
import com.bibliotex.catalog.repositories.BookRepository;
import com.bibliotex.catalog.repositories.CatalogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidatorService {
    private final ImageValidationService imageValidationService;
    private final BookRepository bookRepository;
    private final CatalogRepository catalogRepository;

    public void isValidOrThrow(BookRequest bookRequest) {
        this.isValidImageUrlOrThrow(bookRequest.imageUrl());
        this.isbnDoesNotExistOrThrow(bookRequest.isbn());
        this.titleAndEditionDoesNotExistOrThrow(bookRequest.title(), bookRequest.edition());
    }

    public void isValidOrThrow(MangaRequest mangaRequest) {
        this.isValidImageUrlOrThrow(mangaRequest.imageUrl());
        this.titleAndEditionDoesNotExistOrThrow(mangaRequest.title(), mangaRequest.edition());
    }

    public void isValidOrThrow(ComicRequest comicRequest) {
        this.isValidImageUrlOrThrow(comicRequest.imageUrl());
        this.titleAndEditionDoesNotExistOrThrow(comicRequest.title(), comicRequest.edition());
    }

    public void isValidImageUrlOrThrow(String url) {
        boolean isInvalidUrlImage = imageValidationService.isInvalid(url);

        if (isInvalidUrlImage) throw new IllegalStateException("A imagem fornecida não existe");
    }

    public void isbnDoesNotExistOrThrow(String isbn) {
        boolean isbnAlreadyExists = bookRepository.existsBookByIsbn(isbn);

        if (isbnAlreadyExists) throw new IllegalStateException("Isbn já existe");
    }

    public void titleAndEditionDoesNotExistOrThrow(String title, Integer edition) {
        boolean isbnAlreadyExists = catalogRepository.existsByTitleEqualsIgnoreCaseAndEdition(title, edition);

        if (isbnAlreadyExists) throw new IllegalStateException("Título e edição já está registrado");
    }

}

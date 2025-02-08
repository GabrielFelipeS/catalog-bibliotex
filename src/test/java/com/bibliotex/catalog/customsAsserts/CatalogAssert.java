package com.bibliotex.catalog.customsAsserts;


import com.bibliotex.catalog.domain.dto.request.BookRequest;
import com.bibliotex.catalog.domain.dto.request.ComicRequest;
import com.bibliotex.catalog.domain.dto.request.MangaRequest;
import com.bibliotex.catalog.domain.dto.response.BookResponse;
import com.bibliotex.catalog.domain.dto.response.CatalogResponse;
import com.bibliotex.catalog.domain.dto.response.ComicResponse;
import com.bibliotex.catalog.domain.dto.response.MangaResponse;
import com.bibliotex.catalog.mappers.CatalogMapper;
import org.assertj.core.api.AbstractAssert;

import java.util.List;

public class CatalogAssert extends AbstractAssert<CatalogAssert, CatalogResponse> {

    protected CatalogAssert(CatalogResponse catalog) {
        super(catalog, CatalogAssert.class);
    }

    public static CatalogAssert assertThat(MangaResponse catalog) {
        return new CatalogAssert(CatalogMapper.toDTO(catalog));
    }

    public static CatalogAssert assertThat(BookResponse catalog) {
        return new CatalogAssert(CatalogMapper.toDTO(catalog));
    }

    public static CatalogAssert assertThat(ComicResponse catalog) {
        return new CatalogAssert(CatalogMapper.toDTO(catalog));
    }

    public CatalogAssert isValid(ComicRequest request) {
        isNotNull();

        return this.isValid(request.title(), request.description(), request.pages(),
                request.language(), request.edition(), request.yearOfRelease(),
                request.imageUrl(), request.authorsIds(), request.publisherId());
    }

    public CatalogAssert isValid(BookRequest request) {
        isNotNull();

        return this.isValid(request.title(), request.description(), request.pages(),
                request.language(), request.edition(), request.yearOfRelease(),
                request.imageUrl(), request.authorsIds(), request.publisherId());
    }

    public CatalogAssert isValid(MangaRequest request) {
        isNotNull();

        return this.isValid(request.title(), request.description(), request.pages(),
                request.language(), request.edition(), request.yearOfRelease(),
                request.imageUrl(), request.authorsIds(), request.publisherId());
    }

    public CatalogAssert isValid(String title, String description, Integer pages,
                                 String language, Integer edition, Integer yearOfRelease, String imageUrl,
                                 List<Long> authorsIds, Long publisherId) {
        isNotNull();

        return this
                .hasTitle(title)
                .hasDescription(description)
                .hasEdition(edition)
                .hasPages(pages)
                .hasImageUrl(imageUrl)
                .hasYearOfRelease(yearOfRelease)
                .hasLanguage(language)
                ;

    }


    public CatalogAssert idNotNull() {
        isNotNull();

        if (actual.id() == null) {
            failWithMessage("Esperava que o id não fosse nulo");
        }

        return this;
    }

    public CatalogAssert idIsPositive() {
        isNotNull();

        if (actual.id() <= 0) {
            failWithMessage("Esperava que o id fosse positivo");
        }

        return this;
    }

    public CatalogAssert hasTitle(String title) {
        isNotNull();
        String actualTitle = actual.title();

        if (!actualTitle.equals(title)) {
            failWithMessage("Esperava que o titulo fosse %s, mas era %s", title, actualTitle);
        }

        return this;
    }

    public CatalogAssert hasDescription(String description) {
        isNotNull();

        String actualDescription = actual.description();

        if (!actualDescription.equals(description)) {
            failWithMessage("Esperava que o description fosse %s, mas era %s", description, actualDescription);
        }

        return this;
    }

    public CatalogAssert hasPages(Integer pages) {
        isNotNull();

        Integer actualPages = actual.pages();

        if (!actualPages.equals(pages)) {
            failWithMessage("Esperava que o pages fosse %s, mas era %s", actualPages, pages);
        }

        return this;
    }

    public CatalogAssert hasLanguage(String language) {
        isNotNull();

        String actualLanguage = actual.language();

        if (!actualLanguage.equals(language)) {
            failWithMessage("Esperava que o language fosse %s, mas era %s", actualLanguage, language);
        }

        return this;
    }

    public CatalogAssert hasEdition(Integer edition) {
        isNotNull();

        Integer actualEdition = actual.edition();

        if (!actualEdition.equals(edition)) {
            failWithMessage("Esperava que o edition fosse %s, mas era %s", actualEdition, edition);
        }

        return this;
    }

    public CatalogAssert hasYearOfRelease(Integer yearOfRelease) {
        isNotNull();

        Integer actualYearOfRelease = actual.yearOfRelease();

        if (!actualYearOfRelease.equals(yearOfRelease)) {
            failWithMessage("Esperava que o yearOfRelease fosse %s, mas era %s", actualYearOfRelease, yearOfRelease);
        }

        return this;
    }

    public CatalogAssert hasImageUrl(String imageUrl) {
        isNotNull();

        String actualImageUrl = actual.imageUrl();

        if (!actualImageUrl.equals(imageUrl)) {
            failWithMessage("Esperava que o imageUrl fosse %s, mas era %s", actualImageUrl, imageUrl);
        }

        return this;
    }

    public CatalogAssert titleIsNotBlank() {
        isNotNull();

        String actualTitle = actual.title();
        if (!actualTitle.isBlank()) {
            failWithMessage("Esperava que o titulo não fosse fosse %s vazio", actualTitle);
        }

        return this;
    }

    public CatalogAssert descriptionIsNotBlank() {
        isNotNull();

        String actualDescription = actual.description();
        if (!actualDescription.isBlank()) {
            failWithMessage("Esperava que o titulo não fosse fosse %s vazio", actualDescription);
        }

        return this;
    }

    public CatalogAssert languageIsNotBlank() {
        isNotNull();

        String actualLanguage = actual.language();
        if (!actualLanguage.isBlank()) {
            failWithMessage("Esperava que o titulo não fosse fosse %s vazio", actualLanguage);
        }

        return this;
    }
}

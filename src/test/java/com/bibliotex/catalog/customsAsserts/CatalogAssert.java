package com.bibliotex.catalog.customsAsserts;


import com.bibliotex.catalog.domain.dto.request.BookRequest;
import com.bibliotex.catalog.domain.model.Catalog;
import org.assertj.core.api.AbstractAssert;

import java.util.List;

public class CatalogAssert extends AbstractAssert<CatalogAssert, Catalog> {

    protected CatalogAssert(Catalog catalog) {
        super(catalog, CatalogAssert.class);
    }

    public static CatalogAssert assertThat(Catalog catalog) {
        return new CatalogAssert(catalog);
    }

    public CatalogAssert isValid(BookRequest bookRequest) {
        isNotNull();

        this.isValid(bookRequest.title(), bookRequest.description(), bookRequest.pages(),
                bookRequest.language(), bookRequest.edition(), bookRequest.yearOfRelease(),
                bookRequest.imageUrl(), bookRequest.authorsIds(), bookRequest.publisherId());

        return this;
    }

    public CatalogAssert isValid(String title, String description, Integer pages,
                                 String language, Integer edition, Integer yearOfRelease, String imageUrl,
                                 List<Long> authorsIds, Long publisherId) {
        isNotNull();

        this.hasTitle(title);
        this.hasDescription(description);
        this.hasEdition(edition);
        this.hasPages(pages);
        this.hasImageUrl(imageUrl);
        this.hasYearOfRelease(yearOfRelease);
        this.hasLanguage(language);

        return this;
    }


    public CatalogAssert idNotNull() {
        isNotNull();

        if (actual.getId() == null) {
            failWithMessage("Esperava que o id não fosse nulo");
        }

        return this;
    }

    public CatalogAssert idIsPositive() {
        isNotNull();

        if (actual.getId() <= 0) {
            failWithMessage("Esperava que o id fosse positivo");
        }

        return this;
    }

    public CatalogAssert hasTitle(String title) {
        isNotNull();
        String actualTitle = actual.getTitle();

        if (!actualTitle.equals(title)) {
            failWithMessage("Esperava que o titulo fosse %s, mas era %s", title, actualTitle);
        }

        return this;
    }

    public CatalogAssert hasDescription(String description) {
        isNotNull();

        String actualDescription = actual.getDescription();

        if (!actualDescription.equals(description)) {
            failWithMessage("Esperava que o description fosse %s, mas era %s", description, actualDescription);
        }

        return this;
    }

    public CatalogAssert hasPages(Integer pages) {
        isNotNull();

        Integer actualPages = actual.getPages();

        if (!actualPages.equals(pages)) {
            failWithMessage("Esperava que o pages fosse %s, mas era %s", actualPages, pages);
        }

        return this;
    }

    public CatalogAssert hasLanguage(String language) {
        isNotNull();

        String actualLanguage = actual.getLanguage();

        if (!actualLanguage.equals(language)) {
            failWithMessage("Esperava que o language fosse %s, mas era %s", actualLanguage, language);
        }

        return this;
    }

    public CatalogAssert hasEdition(Integer edition) {
        isNotNull();

        Integer actualEdition = actual.getEdition();

        if (!actualEdition.equals(edition)) {
            failWithMessage("Esperava que o edition fosse %s, mas era %s", actualEdition, edition);
        }

        return this;
    }

    public CatalogAssert hasYearOfRelease(Integer yearOfRelease) {
        isNotNull();

        Integer actualYearOfRelease = actual.getYearOfRelease();

        if (!actualYearOfRelease.equals(yearOfRelease)) {
            failWithMessage("Esperava que o yearOfRelease fosse %s, mas era %s", actualYearOfRelease, yearOfRelease);
        }

        return this;
    }

    public CatalogAssert hasImageUrl(String imageUrl) {
        isNotNull();

        String actualImageUrl = actual.getImageUrl();

        if (!actualImageUrl.equals(imageUrl)) {
            failWithMessage("Esperava que o imageUrl fosse %s, mas era %s", actualImageUrl, imageUrl);
        }

        return this;
    }

    public CatalogAssert titleIsNotBlank() {
        isNotNull();

        String actualTitle = actual.getTitle();
        if (!actualTitle.isBlank()) {
            failWithMessage("Esperava que o titulo não fosse fosse %s vazio", actualTitle);
        }

        return this;
    }

    public CatalogAssert descriptionIsNotBlank() {
        isNotNull();

        String actualDescription = actual.getDescription();
        if (!actualDescription.isBlank()) {
            failWithMessage("Esperava que o titulo não fosse fosse %s vazio", actualDescription);
        }

        return this;
    }

    public CatalogAssert languageIsNotBlank() {
        isNotNull();

        String actualLanguage = actual.getLanguage();
        if (!actualLanguage.isBlank()) {
            failWithMessage("Esperava que o titulo não fosse fosse %s vazio", actualLanguage);
        }

        return this;
    }
}

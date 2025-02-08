package com.bibliotex.catalog.domain.enums;

public enum PublicationStatus {
    CANCELED("Cancelado"),
    ONGOING("Em lançamento"),
    COMPLETED("Concluído"),
    HIATUS("Hiato"),
    DELAYED("Atrasado");

    private final String value;

    PublicationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

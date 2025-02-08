package com.bibliotex.catalog.domain.enums;

public enum ContentRating {
    EVERYONE("Livre"),
    EVERYONE_7("+7"),
    CHILDREN_8("+8"),
    CHILDREN_9("+9"),
    TEEN_10("+10"),
    TEEN_12("+12"),
    TEEN_14("+14"),
    TEEN_16("+16"),
    MATURE("+18"),
    ADULTS_ONLY("+21");

    private final String label;

    ContentRating(String label) {
        this.label = label;
    }

    public String getValue() {
        return label;
    }
}

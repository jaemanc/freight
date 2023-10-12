package com.express.freight.common.dto;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Category {
    OPERATE("operate"),
    SPEND("spend"),
    MAINTENANCE("maintenance"),
    REFUEL("refuel");

    private final String label;

    Category(String label) {
        this.label = label;
    }
    public String label() {
        return label;
    }

    private static final Map<String, Category> BY_LABEL =
        Stream.of(values()).collect(Collectors.toMap(Category::label, Function.identity()));

    public static Category find(String label) {
        Category category = BY_LABEL.get(label);
        if (category == null) {
            throw new IllegalArgumentException("No Enum with label " + label);
        }
        return category;
    }

}

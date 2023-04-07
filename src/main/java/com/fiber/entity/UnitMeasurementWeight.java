package com.fiber.entity;

import java.util.stream.Stream;

public enum UnitMeasurementWeight {

    POUND_LBS,
    KILOGRAMS,
    OUNCE;

    public static UnitMeasurementWeight getByName(String name) {
        return Stream.of(values())
                .filter(e -> e.name().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}

package com.fiber.entity;

import java.util.stream.Stream;

public enum UnitMeasurementHeight {

    CENTIMETER,
    FOOT;

    public static UnitMeasurementHeight getByName(String name) {
        return Stream.of(values())
                .filter(e -> e.name().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}

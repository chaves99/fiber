package com.fiber.payload;

public record UserUpdateRequestPayload(
        String name,
        String email,
        Double weight,
        Double height,
        String weightUnit,
        String heightUnit) {
}

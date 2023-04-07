package com.fiber.payload;

public record UserUpdateRequestPayload(
        String name,
        String email) {
}

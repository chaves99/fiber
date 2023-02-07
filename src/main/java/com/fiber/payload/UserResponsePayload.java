package com.fiber.payload;

public record UserResponsePayload(Long id, String name, String email, String password, Double goalCalories,
                                  Double goalCarbohydrate, Double goalProtein) {
}

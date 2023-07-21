package com.fiber.payload.http.meal;

public record FoodMealRequestPayload(
        Long id,
        Double quantity
) {
}

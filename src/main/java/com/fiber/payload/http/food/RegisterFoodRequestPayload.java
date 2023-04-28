package com.fiber.payload.http.food;

public record RegisterFoodRequestPayload(String name, Double baseQuantity,
                                         Double carbohydrate, Double protein,
                                         Double fiber, Double calories, Double fat) {
}

package com.fiber.payload;

public record RegisterFoodRequestPayload(String name, Double baseQuantity,
                                         Double carbohydrate, Double protein,
                                         Double fiber, Double calories) {
}

package com.fiber.payload.http.food;

import com.fiber.entity.FoodEntity;

import java.util.List;

public record RegisterFoodRequestPayload(String name, Double baseQuantity,
                                         Double carbohydrate, Double protein,
                                         Double fiber, Double calories, Double fat) {

    public static List<FoodEntity> toEntity(List<RegisterFoodRequestPayload> foods) {
        return foods.stream().map(FoodEntity::from).toList();
    }
}

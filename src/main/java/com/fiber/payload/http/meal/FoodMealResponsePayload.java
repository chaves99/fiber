package com.fiber.payload.http.meal;

import com.fiber.entity.FoodPerMealEntity;

import java.util.List;

public record FoodMealResponsePayload(
        Long id,
        String name,
        Double quantity
) {
    public static List<FoodMealResponsePayload> from(List<FoodPerMealEntity> foodPerMeal) {
        return foodPerMeal.stream()
                .map(fpm ->
                        new FoodMealResponsePayload(fpm.getKey().getFood().getId(), fpm.getKey().getFood().getName(), fpm.getQuantity())
                ).toList();
    }
}

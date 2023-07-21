package com.fiber.payload.http.food;

import com.fiber.entity.FoodEntity;

import java.util.List;

public record SimpleFoodResponsePayload(
        Long id,
        String name,
        Double baseQuantity,
        Double carbohydrate,
        Double protein,
        Double fiber,
        Double calories,
        Double fat
) {
    public static SimpleFoodResponsePayload fromEntity(FoodEntity entity) {
        return new SimpleFoodResponsePayload(entity.getId(),
                entity.getName(),
                entity.getBaseQuantity(),
                entity.getCarbohydrate(),
                entity.getProtein(),
                entity.getFiber(),
                entity.getCalories(),
                entity.getFat());
    }

    public static List<SimpleFoodResponsePayload> fromEntity(List<FoodEntity> entities) {
        return entities
                .stream()
                .map(SimpleFoodResponsePayload::fromEntity)
                .toList();
    }
}

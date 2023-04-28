package com.fiber.payload.http.season;

import com.fiber.entity.MealEntity;

import java.util.List;
import java.util.stream.Collectors;

public record MealResponsePayload(
        Long id
) {
    public static List<MealResponsePayload> fromEntity(List<MealEntity> meals) {
        return meals
                .stream()
                .map(MealResponsePayload::fromEntity)
                .collect(Collectors.toList());
    }

    public static MealResponsePayload fromEntity(MealEntity meal) {
        return new MealResponsePayload(meal.getId());
    }
}

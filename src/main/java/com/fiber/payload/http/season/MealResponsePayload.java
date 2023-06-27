package com.fiber.payload.http.season;

import com.fiber.entity.MealEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public record MealResponsePayload(
        Long id,
        LocalDate day,
        LocalTime time,
        int order
) {
    public static List<MealResponsePayload> fromEntity(List<MealEntity> meals) {
        return meals
                .stream()
                .map(MealResponsePayload::fromEntity)
                .collect(Collectors.toList());
    }

    public static MealResponsePayload fromEntity(MealEntity meal) {
        return new MealResponsePayload(meal.getId(), meal.getDay(), meal.getTime(), meal.getOrder());
    }
}

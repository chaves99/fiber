package com.fiber.payload.http.season;

import com.fiber.entity.MealDayEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record MealDayResponsePayload(
        Long id,
        LocalDate date,
        List<MealResponsePayload> meal
) {
    public static List<MealDayResponsePayload> fromEntity(List<MealDayEntity> mealDays) {
        return mealDays
                .stream()
                .map(MealDayResponsePayload::fromEntity)
                .collect(Collectors.toList());
    }

    public static MealDayResponsePayload fromEntity(MealDayEntity mealDays) {
        return new MealDayResponsePayload(mealDays.getId(), mealDays.getDay(), MealResponsePayload.fromEntity(mealDays.getMeals()));
    }
}

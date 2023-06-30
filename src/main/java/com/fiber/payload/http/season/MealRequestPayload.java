package com.fiber.payload.http.season;

import com.fiber.entity.MealEntity;

import java.time.LocalDateTime;
import java.util.List;

public record MealRequestPayload(
        Long seasonId,
        String description,
        Integer order,
        List<Long> foods
) {

    public MealEntity toEntity() {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setDayTime(LocalDateTime.now());
        mealEntity.setDescription(description());
        mealEntity.setOrder(order());
        return mealEntity;
    }

}

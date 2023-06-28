package com.fiber.payload.http.season;

import com.fiber.entity.MealEntity;
import com.fiber.payload.http.food.RegisterFoodRequestPayload;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record MealRequestPayload(
        Long seasonId,
        String description,
        List<Long> foods
) {

    public MealEntity toEntity() {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setTime(LocalTime.now());
        mealEntity.setDay(LocalDate.now());
        mealEntity.setDescription(description());
        return mealEntity;
    }

}

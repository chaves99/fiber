package com.fiber.payload.http.season;

import com.fiber.entity.MealEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record MealRequestPayload(
        Long seasonId,
        String description,
        Integer order,
        @NotNull(message = "foods field cannot be null")
        @NotEmpty(message = "foods field cannot be empty")
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

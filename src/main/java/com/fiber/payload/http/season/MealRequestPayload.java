package com.fiber.payload.http.season;

import com.fiber.entity.MealEntity;
import com.fiber.payload.http.food.RegisterFoodRequestPayload;

import java.util.List;

public record MealRequestPayload(
        String description,
        List<RegisterFoodRequestPayload> foods
) {

}

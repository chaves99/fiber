package com.fiber.payload.http.season;

import com.fiber.entity.MealEntity;
import com.fiber.payload.http.food.RegisterFoodRequestPayload;

import java.util.List;

public record MealRequestPayload(
        List<RegisterFoodRequestPayload> foods
) {

    public MealEntity toEntity() {
        List<RegisterFoodRequestPayload> foods = foods() != null ? foods() : List.of();
        return new MealEntity(null, null, RegisterFoodRequestPayload.toEntity(foods), null);
    }

    public static List<MealEntity> toEntity(List<MealRequestPayload> list) {
        return list.stream().map(MealRequestPayload::toEntity).toList();
    }
}

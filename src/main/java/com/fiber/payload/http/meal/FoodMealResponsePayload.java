package com.fiber.payload.http.meal;

import com.fiber.entity.FoodEntity;
import com.fiber.entity.FoodPerMealEntity;
import com.fiber.util.FoodMacroTotalizer;

import java.util.List;

public record FoodMealResponsePayload(
        Long id,
        String name,
        Double quantity,
        Double totalCarbs,
        Double totalKcal,
        Double totalFat,
        Double totalFiber,
        Double totalProtein
) {
    public static List<FoodMealResponsePayload> from(List<FoodPerMealEntity> foodPerMeal) {
        return foodPerMeal.stream()
                .map(fpm ->
                        {
                            FoodEntity food = fpm.getKey().getFood();
                            FoodMacroTotalizer totalizer = new FoodMacroTotalizer(food, fpm.getQuantity());

                            return new FoodMealResponsePayload(food.getId(), food.getName(), fpm.getQuantity(),
                                    totalizer.getCarbs(), totalizer.getKcal(), totalizer.getFat(),
                                    totalizer.getFiber(), totalizer.getProtein());
                        }
                ).toList();
    }
}

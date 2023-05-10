package com.fiber.payload.http.season;

import com.fiber.entity.DietSeasonEntity;

import java.time.LocalDate;
import java.util.List;

public record SeasonResponsePayload(
        Long id,
        String name,
        String description,
        Double caloriesGoal,
        Double carbohydrateGoal,
        Double proteinGoal,
        Double fatGoal,
        LocalDate initialDate,
        LocalDate finalDate,
        Boolean active,
        List<MealDayResponsePayload> mealDay
) {

    public static SeasonResponsePayload fromEntity(DietSeasonEntity entity) {
        List<MealDayResponsePayload> mealDay = MealDayResponsePayload
                .fromEntity(entity.getMealDays() == null ? List.of() : entity.getMealDays());
        return new SeasonResponsePayload(entity.getId(), entity.getName(),
                entity.getDescription(), entity.getCaloriesGoal(), entity.getCarbohydrateGoal(),
                entity.getProteinGoal(), entity.getFatGoal(), entity.getInitialDate(),
                entity.getFinalDate(), entity.getActive(), mealDay);
    }
}

package com.fiber.payload.http.season;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;

public record SeasonCreateRequestPayload(
        String name,
        String description,
        Double caloriesGoal,
        Double carbohydrateGoal,
        Double proteinGoal,
        Double fatGoal,
        LocalDate initialDate,
        LocalDate finalDate,
        Long userId
) {
    public DietSeasonEntity toEntity(UserEntity user, Boolean active) {
        return new DietSeasonEntity(null, name(),
                description(), caloriesGoal(), carbohydrateGoal(),
                proteinGoal(), fatGoal(), initialDate(), finalDate(),
                active, user, List.of());
    }
}

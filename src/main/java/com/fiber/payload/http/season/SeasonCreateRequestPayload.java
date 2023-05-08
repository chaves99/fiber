package com.fiber.payload.http.season;

import java.time.LocalDate;

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
}

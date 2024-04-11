package com.fiber.payload.http.report;

public record FoodReportResponsePayload(
        String name,
        Double quantity,
        Double kcal,
        Double carbs,
        Double fat,
        Double protein
) {
}

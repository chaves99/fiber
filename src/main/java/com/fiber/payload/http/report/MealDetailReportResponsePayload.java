package com.fiber.payload.http.report;

import java.time.LocalTime;
import java.util.List;

public record MealDetailReportResponsePayload(
        String description,
        LocalTime hour,
        List<FoodReportResponsePayload> foods
) {
}

package com.fiber.payload.http.report;

import java.util.List;

public record MealReportResponsePayload(
        Long seasonId,
        List<MealDaysReportResponsePayload> mealDays
) {
}

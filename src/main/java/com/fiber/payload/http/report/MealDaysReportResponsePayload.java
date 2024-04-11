package com.fiber.payload.http.report;

import java.time.LocalDate;
import java.util.List;

public record MealDaysReportResponsePayload(
        LocalDate day,
        List<MealDetailReportResponsePayload> meals
) {
}

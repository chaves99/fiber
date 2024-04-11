package com.fiber.controller;

import com.fiber.payload.http.report.MealReportResponsePayload;
import com.fiber.service.AuthenticatedUserRetrieverService;
import com.fiber.service.MealReportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "meal-report")
@AllArgsConstructor
@Tag(name = "meal-report")
public class MealReportController {

    private final MealReportService mealReportService;

    private final AuthenticatedUserRetrieverService authenticatedUserRetrieverService;

    @GetMapping
    public MealReportResponsePayload report(Authentication authentication) {
        return authenticatedUserRetrieverService
                .retrieve(authentication)
                .map(user -> mealReportService.get(user.getId()))
                .orElse(null);
    }

}

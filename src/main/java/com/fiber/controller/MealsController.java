package com.fiber.controller;

import com.fiber.payload.http.season.MealDayCreateRequestPayload;
import com.fiber.payload.http.season.MealDayResponsePayload;
import com.fiber.service.MealsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "meal-day")
public class MealsController {

    private final MealsService mealsService;

    @PostMapping()
    public ResponseEntity<MealDayResponsePayload> createMeal(MealDayCreateRequestPayload payload) {
        return ResponseEntity.ok(mealsService.createMeal(payload));
    }

}

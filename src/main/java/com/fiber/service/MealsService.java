package com.fiber.service;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.payload.http.season.MealDayCreateRequestPayload;
import com.fiber.payload.http.season.MealDayResponsePayload;
import com.fiber.repository.MealDayRepository;
import com.fiber.repository.MealRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MealsService {

    private final MealRepository mealRepository;

    private final MealDayRepository mealDayRepository;

    private final SeasonService seasonService;

    public MealDayResponsePayload createMeal(MealDayCreateRequestPayload payload) {
        DietSeasonEntity dietSeason = null;
        return null;
    }
}

package com.fiber.service;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.entity.MealEntity;
import com.fiber.error.excption.ResourceNotFoundException;
import com.fiber.payload.http.season.MealRequestPayload;
import com.fiber.payload.http.season.MealResponsePayload;
import com.fiber.repository.MealRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MealsService {

    private final MealRepository mealRepository;

    private final SeasonService seasonService;

    private final FoodService foodService;

    public MealResponsePayload create(MealRequestPayload payload) {
        Long seasonId = payload.seasonId();
        DietSeasonEntity dietSeasonEntity = seasonService.getDietSeason(seasonId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found season with id:" + seasonId));

        MealEntity entity = payload.toEntity();
        return null;
    }
}

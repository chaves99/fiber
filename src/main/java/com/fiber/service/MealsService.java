package com.fiber.service;

import com.fiber.entity.*;
import com.fiber.error.excption.ResourceNotFoundException;
import com.fiber.payload.http.meal.FoodMealRequestPayload;
import com.fiber.payload.http.meal.MealRequestPayload;
import com.fiber.payload.http.meal.MealResponsePayload;
import com.fiber.payload.http.season.SeasonCreateRequestPayload;
import com.fiber.repository.FoodPerMealRepository;
import com.fiber.repository.MealRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MealsService {

    private final MealRepository mealRepository;

    private final SeasonService seasonService;

    private final FoodService foodService;

    private final FoodPerMealRepository foodPerMealRepository;

    public MealResponsePayload create(MealRequestPayload payload, Long userId) {
        log.info("create - payload:{}", payload);
        DietSeasonEntity season = getSeason(payload.seasonId())
                .or(() -> seasonService.getActiveByUserId(userId))
                .orElseGet(() -> seasonService.createDefault(userId));
        MealEntity mealEntity = MealEntity.toEntity(season, payload);
        if (mealEntity.getOrder() == null) {
            mealEntity.setOrder(mealRepository.countByDay(mealEntity.getDayTime().toLocalDate()));
        }
        List<FoodPerMealEntity> foodPerMealEntities = saveFoodPerMeal(mealEntity, payload.foods());
        mealEntity.setFoodPerMeal(foodPerMealEntities);
        MealEntity saved = mealRepository.save(mealEntity);
        return MealResponsePayload.fromEntity(saved);
    }

//    private DietSeasonEntity getOrCreateSeason(MealRequestPayload payload, Long userId) {
//        Optional<DietSeasonEntity> season = getSeason(payload.seasonId());
//        if (season.isPresent()) {
//            return season.get();
//        }
//        season = seasonService.getActiveByUserId(userId);
//        return season.orElseGet(() -> seasonService.createDefault(userId));
//    }

    public List<MealResponsePayload> get(Long idSeason) {
        log.info("get - season id:{}", idSeason);
        DietSeasonEntity season = getSeason(idSeason).orElseThrow(() -> new ResourceNotFoundException("Not found season with id:" + idSeason));
        List<MealEntity> meals = mealRepository.findBySeason(season);
        log.info("get - meals size:{}", meals.size());
        return MealResponsePayload.fromEntity(meals);
    }

    private Optional<DietSeasonEntity> getSeason(Long idSeason) {
        if (idSeason != null) {
            return seasonService.getDietSeason(idSeason);
        }
        return Optional.empty();
    }

    private List<FoodPerMealEntity> saveFoodPerMeal(MealEntity meal, List<FoodMealRequestPayload> foods) {
        List<FoodEntity> foodEntities = foodService.getById(foods.stream().map(FoodMealRequestPayload::id).toList());
        return foods.stream().map(f -> {
            FoodEntity foodEntity = foodEntities.stream()
                    .filter(food -> food.getId().equals(f.id()))
                    .findFirst()
                    .orElseThrow();

            return new FoodPerMealEntity(new FoodPerMealPK(meal, foodEntity), f.quantity());
        }).toList();
    }
}

package com.fiber.service;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.entity.FoodEntity;
import com.fiber.entity.FoodPerMealEntity;
import com.fiber.entity.FoodPerMealPK;
import com.fiber.entity.MealEntity;
import com.fiber.error.excption.ResourceNotFoundException;
import com.fiber.payload.http.meal.FoodMealRequestPayload;
import com.fiber.payload.http.meal.MealRequestPayload;
import com.fiber.payload.http.meal.MealResponsePayload;
import com.fiber.repository.FoodPerMealRepository;
import com.fiber.repository.MealRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class MealsService {

    private final MealRepository mealRepository;

    private final SeasonService seasonService;

    private final FoodService foodService;

    private final FoodPerMealRepository foodPerMealRepository;

    public MealResponsePayload create(MealRequestPayload payload) {
        log.info("create - payload:{}", payload);
        DietSeasonEntity season = getSeason(payload.seasonId());
        MealEntity mealEntity = MealEntity.toEntity(season, payload);
        if (mealEntity.getOrder() == null) {
            mealEntity.setOrder(mealRepository.countByDay(mealEntity.getDayTime().toLocalDate()));
        }
        List<FoodPerMealEntity> foodPerMealEntities = saveFoodPerMeal(mealEntity, payload.foods());
        mealEntity.setFoodPerMeal(foodPerMealEntities);
        MealEntity saved = mealRepository.save(mealEntity);
        return MealResponsePayload.fromEntity(saved);
    }

    public List<MealResponsePayload> get(Long idSeason) {
        log.info("get - season id:{}", idSeason);
        DietSeasonEntity season = getSeason(idSeason);
        List<MealEntity> meals = mealRepository.findBySeason(season);
        log.info("get - meals size:{}", meals.size());
        return MealResponsePayload.fromEntity(meals);
    }

    private DietSeasonEntity getSeason(Long idSeason) {
        return seasonService.getDietSeason(idSeason)
                .orElseThrow(() -> new ResourceNotFoundException("Not found season with id:" + idSeason));
    }

    private List<FoodPerMealEntity> saveFoodPerMeal(MealEntity meal, List<FoodMealRequestPayload> foods) {
        List<FoodEntity> foodEntities = foodService.getById(foods.stream().map(FoodMealRequestPayload::id).toList());
        List<FoodPerMealEntity> list = foods.stream().map(f -> {
            FoodEntity foodEntity = foodEntities.stream()
                    .filter(food -> food.getId().equals(f.id()))
                    .findFirst()
                    .orElseThrow();

            return new FoodPerMealEntity(new FoodPerMealPK(meal, foodEntity), f.quantity());
        }).toList();

//        return foodPerMealRepository.saveAll(list);
        return list;
    }
}

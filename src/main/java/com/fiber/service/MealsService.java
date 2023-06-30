package com.fiber.service;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.entity.FoodEntity;
import com.fiber.entity.MealEntity;
import com.fiber.error.excption.ResourceNotFoundException;
import com.fiber.payload.http.season.MealRequestPayload;
import com.fiber.payload.http.season.MealResponsePayload;
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

    public MealResponsePayload create(MealRequestPayload payload) {
        log.info("create - payload:{}", payload);
        Long seasonId = payload.seasonId();
        DietSeasonEntity season = getSeason(seasonId);
        List<FoodEntity> foods = foodService.getById(payload.foods());
        MealEntity mealEntity = MealEntity.toEntity(season, foods, payload);
        if(mealEntity.getOrder() == null) {
            mealEntity.setOrder(mealRepository.countByDay(mealEntity.getDayTime().toLocalDate()));
        }
        return MealResponsePayload.fromEntity(mealRepository.save(mealEntity));
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
}

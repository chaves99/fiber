package com.fiber.service;

import com.fiber.payload.http.food.RegisterFoodRequestPayload;
import com.fiber.payload.http.season.MealDayCreateRequestPayload;
import com.fiber.payload.http.season.MealRequestPayload;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class MealsServicesTest {

    Map<Integer, MealDayCreateRequestPayload> createParams = null;

    private final Integer
            COMPLETE_PAYLOAD = 0,
            NO_USER_PAYLOAD = 1,
            NO_DATE_PAYLOAD = 2,
            NO_SEASON_PAYLOAD = 3;

    @BeforeAll
    public void createScenarios() {

        List<RegisterFoodRequestPayload> foodRequestPayloads =
                List.of(new RegisterFoodRequestPayload("Cooked Rice", 100D, 38D, 2D, 2D, 150D, 5D),
                        new RegisterFoodRequestPayload("Cooked Bean", 100D, 48D, 5D, 7D, 230D, 5D),
                        new RegisterFoodRequestPayload("Cooked Chicken", 100D, 0D, 35D, 0D, 120D, 3D),
                        new RegisterFoodRequestPayload("Cooked Pumpkin", 100D, 15D, 0D, 15D, 80D, 0D));

        List<MealRequestPayload> mealRequestPayloads = List.of(
                new MealRequestPayload(foodRequestPayloads),
                new MealRequestPayload(foodRequestPayloads)
        );

        MealDayCreateRequestPayload completePayload = new MealDayCreateRequestPayload(
                1L, LocalDate.now(), 1L, mealRequestPayloads);

        MealDayCreateRequestPayload noUserPayload = new MealDayCreateRequestPayload(
                null, LocalDate.now(), 1L, mealRequestPayloads);

        MealDayCreateRequestPayload noDatePayload = new MealDayCreateRequestPayload(
                1L, null, 1L, mealRequestPayloads);

        MealDayCreateRequestPayload noSeasonPayload = new MealDayCreateRequestPayload(
                1L, LocalDate.now(), 1L, mealRequestPayloads);

        createParams.put(COMPLETE_PAYLOAD, completePayload);
        createParams.put(NO_USER_PAYLOAD, noUserPayload);
        createParams.put(NO_DATE_PAYLOAD, noDatePayload);
        createParams.put(NO_SEASON_PAYLOAD, noSeasonPayload);

    }
}

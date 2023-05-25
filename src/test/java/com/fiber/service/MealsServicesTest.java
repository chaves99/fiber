package com.fiber.service;

import com.fiber.payload.http.season.MealDayCreateRequestPayload;
import com.fiber.payload.http.season.MealDayResponsePayload;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MealsServicesTest {

    @InjectMocks
    MealsService service;

    @Mock
    MealDayRepository mealDayRepository;

    @Mock
    SeasonService seasonService;


    @Test
    public void createSuccess() {
        MealDayCreateRequestPayload payload = new MealDayCreateRequestPayload(
                1L, LocalDate.now(), 1L, List.of());
        MealDayEntity mealDayEntity = MealDayEntity.builder().id(1L).meals(List.of()).build();

        Mockito
                .when(mealDayRepository.save(Mockito.any()))
                .thenReturn(mealDayEntity);

        MealDayResponsePayload responsePayload = service.createMeal(payload);

        Assertions.assertTrue(responsePayload.id() != null);
    }

}

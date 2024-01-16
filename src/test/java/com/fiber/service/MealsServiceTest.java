package com.fiber.service;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.entity.FoodEntity;
import com.fiber.entity.MealEntity;
import com.fiber.error.excption.ResourceNotFoundException;
import com.fiber.payload.http.meal.FoodMealRequestPayload;
import com.fiber.payload.http.meal.MealRequestPayload;
import com.fiber.repository.MealRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MealsServiceTest {

    @InjectMocks
    MealsService service;

    @Mock
    SeasonService seasonService;

    @Mock
    MealRepository mealRepository;

    @Mock
    FoodService foodService;

    @Test
    void create_shouldCreateMeal() {
        List<FoodMealRequestPayload> foods = List.of(
                new FoodMealRequestPayload(1L, 80D),
                new FoodMealRequestPayload(2L, 120D)
        );
        MealRequestPayload payload = new MealRequestPayload(1L, "Description meal", 0, foods);
        ArgumentCaptor<MealEntity> captor = ArgumentCaptor.forClass(MealEntity.class);
        when(seasonService.getDietSeason(anyLong()))
                .thenReturn(Optional.of(new DietSeasonEntity()));
        when(foodService.getById(anyList()))
                .thenReturn(foods.stream().map(f -> FoodEntity.builder().id(f.id()).build()).toList());
        doReturn(mock(MealEntity.class)).when(mealRepository).save(captor.capture());

        service.create(payload, 0L);

        assertThat(captor.getValue()).isNotNull();
    }

}

package com.fiber.service;

import com.fiber.entity.MealEntity;
import com.fiber.payload.http.food.RegisterFoodRequestPayload;
import com.fiber.payload.http.season.MealRequestPayload;
import com.fiber.repository.MealRepository;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MealsServiceTest {

    @InjectMocks
    MealsService service;

    @Mock
    SeasonService seasonService;

    @Mock
    MealRepository mealRepository;

    void create_shoulCreateMeal() {
        List<RegisterFoodRequestPayload> foods = List.of(
                new RegisterFoodRequestPayload("Rice", 100d, 28d, 2d, 1d, 180d, 0d),
                new RegisterFoodRequestPayload("Steak", 100d, 0d, 25d, 1d, 180d, 20d));
        MealRequestPayload payload = new MealRequestPayload(1l, "Description meal", foods);
        ArgumentCaptor<MealEntity> captor = ArgumentCaptor.forClass(MealEntity.class);
        doReturn(mock(MealEntity.class)).when(mealRepository).save(captor.capture());
        service.create(payload);
//        verify(mealRepository, times(1)).save(any());
        assertThat(captor.getValue()).isNotNull();
    }

}

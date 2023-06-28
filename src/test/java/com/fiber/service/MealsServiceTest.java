package com.fiber.service;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.entity.MealEntity;
import com.fiber.error.excption.ResourceNotFoundException;
import com.fiber.payload.http.season.MealRequestPayload;
import com.fiber.repository.MealRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Mock
    FoodService foodService;

    @Test
    void create_shouldCreateMeal() {
        MealRequestPayload payload = new MealRequestPayload(1L, "Description meal", List.of(1L, 2L));
        ArgumentCaptor<MealEntity> captor = ArgumentCaptor.forClass(MealEntity.class);
        when(seasonService.getDietSeason(anyLong())).thenReturn(Optional.of(new DietSeasonEntity()));
        doReturn(mock(MealEntity.class)).when(mealRepository).save(captor.capture());

        service.create(payload);

        assertThat(captor.getValue()).isNotNull();
    }

    @Test
    void create_shouldNotAcceptNullSeasonId() {
        MealRequestPayload payload = new MealRequestPayload(null, "Description meal", List.of(1L, 2L));

        assertThatThrownBy(() -> service.create(payload)).isInstanceOf(ResourceNotFoundException.class);
    }

}

package com.fiber.mapper;

import com.fiber.repository.MealRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MealMapper {

    private final MealRepository mealRepository;

}

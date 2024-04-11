package com.fiber.projection;

import com.fiber.util.FoodMacroTotalizer.FoodTotalizer;

import java.time.LocalDateTime;

public interface MealReportProjection extends FoodTotalizer {

    public Long getSeasonId();

    public Long getSeasonName();

    public Long getMealId();

    public String getMealDescription();

    public LocalDateTime getDayTime();

    public Double getQuantity();

    public String getFoodName();

    public Double getBaseQuantity();

    public Double getCalories();

    public Double getCarbohydrate();

    public Double getFiber();

    public Double getProtein();

    public Double getFat();
}

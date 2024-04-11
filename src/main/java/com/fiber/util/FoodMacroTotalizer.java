package com.fiber.util;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public final class FoodMacroTotalizer {

    private final FoodTotalizer food;

    private final Double quantity;

    private Double calc(Double baseQuantity, Double baseMacro, Double quantity) {
        BigDecimal multiply = BigDecimal
                .valueOf(quantity)
                .multiply(BigDecimal.valueOf(baseMacro));
        return multiply.setScale(2).divide(BigDecimal.valueOf(baseQuantity)).doubleValue();
    }

    public Double getCarbs() {
        return calc(food.getBaseQuantity(), food.getCarbohydrate(), quantity);
    }

    public Double getProtein() {
        return calc(food.getBaseQuantity(), food.getProtein(), quantity);
    }

    public Double getFat() {
        return calc(food.getBaseQuantity(), food.getFat(), quantity);
    }

    public Double getKcal() {
        return calc(food.getBaseQuantity(), food.getCalories(), quantity);
    }

    public Double getFiber() {
        return calc(food.getBaseQuantity(), food.getFiber(), quantity);
    }

    public interface FoodTotalizer {
        Double getBaseQuantity();

        Double getFat();

        Double getCalories();

        Double getFiber();

        Double getCarbohydrate();

        Double getProtein();
    }

}

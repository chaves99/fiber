package com.fiber.entity;

import com.fiber.payload.RegisterFoodRequestPayload;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "food")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Double baseQuantity;

    private Double carbohydrate;

    private Double protein;

    private Double fiber;

    private Double calories;

    private Double fat;

    public static FoodEntity from(RegisterFoodRequestPayload payload) {
        return new FoodEntity(null, payload.name(),
                payload.baseQuantity(), payload.carbohydrate(),
                payload.protein(), payload.fiber(),
                payload.calories(), payload.fat());
    }

    public void update(RegisterFoodRequestPayload payload) {
        if (payload.name() != null) {
            setName(payload.name());
        }
        if (payload.baseQuantity() != null) {
            setBaseQuantity(payload.baseQuantity());
        }
        if (payload.carbohydrate() != null) {
            setCarbohydrate(payload.carbohydrate());
        }
        if (payload.protein() != null) {
            setProtein(payload.protein());
        }
        if (payload.fiber() != null) {
            setFiber(payload.fiber());
        }
        if (payload.calories() != null) {
            setCalories(payload.calories());
        }
        if (payload.fat() != null) {
            setFat(payload.fat());
        }
    }
}

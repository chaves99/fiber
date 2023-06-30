package com.fiber.entity;

import com.fiber.payload.http.season.MealRequestPayload;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "meal")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private LocalDateTime dayTime;

    @Column(name = "meal_order")
    private Integer order;

    @ManyToMany
    @JoinTable(
            name = "food_per_meal",
            joinColumns = @JoinColumn(name = "id_meal"),
            inverseJoinColumns = @JoinColumn(name = "id_food")
    )
    private List<FoodEntity> foods;

    @ManyToOne
    @JoinColumn(name = "id_diet_season")
    private DietSeasonEntity season;

    public static MealEntity toEntity(DietSeasonEntity season, List<FoodEntity> foods, MealRequestPayload payload) {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setFoods(foods);
        mealEntity.setSeason(season);
        mealEntity.setDayTime(LocalDateTime.now());
        mealEntity.setDescription(payload.description());
        mealEntity.setOrder(payload.order());
        return mealEntity;
    }
}

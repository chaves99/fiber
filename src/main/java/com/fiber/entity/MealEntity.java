package com.fiber.entity;

import com.fiber.payload.http.season.MealRequestPayload;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
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

    private LocalDate day;

    private LocalTime time;

    private int order;

    @ManyToMany
    @JoinTable(
            name = "food_per_meal",
            joinColumns = @JoinColumn(name = "id_meal"),
            inverseJoinColumns = @JoinColumn(name = "id_food")
    )
    private List<FoodEntity> foods;

    @ManyToOne
    private DietSeasonEntity season;

    public static MealEntity toEntity(DietSeasonEntity season, List<FoodEntity> foods, MealRequestPayload payload) {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setFoods(foods);
        mealEntity.setSeason(season);
        mealEntity.setDay(LocalDate.now());
        mealEntity.setTime(LocalTime.now());
        mealEntity.setDescription(payload.description());
        return mealEntity;
    }
}

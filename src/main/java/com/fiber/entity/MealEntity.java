package com.fiber.entity;

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

    @ManyToMany(mappedBy = "meals")
    private List<DietSeasonEntity> mealDays;
}

package com.fiber.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Table(name = "diet_season")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DietSeasonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Double caloriesGoal;

    private Double carbohydrateGoal;

    private Double proteinGoal;

    private Double fatGoal;

    private LocalDate initialDate;

    private LocalDate finalDate;

    private Boolean active;

    @OneToMany
    private List<MealDayEntity> mealDays;
}

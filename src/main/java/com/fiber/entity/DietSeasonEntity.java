package com.fiber.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Table(name = "diet_season")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DietSeasonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private UserEntity user;

    @OneToMany
    private List<MealDayEntity> mealDays;
}

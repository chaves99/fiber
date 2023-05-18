package com.fiber.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Table(name = "meal_day")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MealDayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate day;

    @ManyToOne
    @JoinColumn(name = "id_diet_season")
    private DietSeasonEntity dietSeason;

    @ManyToMany
    @JoinTable(
            name = "meal_per_day",
            joinColumns = @JoinColumn(name = "id_meal_day"),
            inverseJoinColumns = @JoinColumn(name = "id_meal")
    )
    private List<MealEntity> meals;

}

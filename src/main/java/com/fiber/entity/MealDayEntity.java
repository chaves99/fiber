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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;

    @OneToMany
    private List<MealEntity> meals;

}

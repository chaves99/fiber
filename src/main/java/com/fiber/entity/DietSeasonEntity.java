package com.fiber.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fiber.payload.http.season.SeasonResponsePayload;
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
@Builder
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "season")
    @JsonManagedReference
    private List<MealEntity> meals;

    public SeasonResponsePayload toResponsePayload() {
        return new SeasonResponsePayload(getId(), getName(),
                getDescription(), getCaloriesGoal(), getCarbohydrateGoal(),
                getProteinGoal(), getFatGoal(), getInitialDate(),
                getFinalDate(), getActive());
    }
}

package com.fiber.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fiber.payload.http.meal.MealRequestPayload;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class MealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private LocalDateTime dayTime;

    @Column(name = "meal_order")
    private Integer order;

    @OneToMany(mappedBy = "key.meal", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonManagedReference
    private List<FoodPerMealEntity> foodPerMeal;

    @ManyToOne
    @JoinColumn(name = "id_diet_season")
    @JsonBackReference
    private DietSeasonEntity season;

    // TODO it needs to handle replicated food
    public static MealEntity toEntity(DietSeasonEntity season, MealRequestPayload payload) {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setSeason(season);
        mealEntity.setDayTime(LocalDateTime.now());
        mealEntity.setDescription(payload.description());
        mealEntity.setOrder(payload.order());
        return mealEntity;
    }
}

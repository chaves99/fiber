package com.fiber.repository;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.entity.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<MealEntity, Long> {

    List<MealEntity> findBySeason(DietSeasonEntity season);

    @Query("select count(meal) from MealEntity meal where date(meal.dayTime) = ?1")
    int countByDay(LocalDate day);
}

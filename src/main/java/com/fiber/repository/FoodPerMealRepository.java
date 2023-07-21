package com.fiber.repository;

import com.fiber.entity.FoodPerMealEntity;
import com.fiber.entity.FoodPerMealPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodPerMealRepository extends JpaRepository<FoodPerMealEntity, FoodPerMealPK> {
}

package com.fiber.repository;

import com.fiber.entity.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<MealEntity, Long> {
}

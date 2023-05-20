package com.fiber.repository;

import com.fiber.entity.MealDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealDayRepository extends JpaRepository<MealDayEntity, Long> {
}

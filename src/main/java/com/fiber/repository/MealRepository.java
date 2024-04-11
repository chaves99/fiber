package com.fiber.repository;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.entity.MealEntity;
import com.fiber.projection.MealReportProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<MealEntity, Long> {

    List<MealEntity> findBySeasonOrderByDayTimeDesc(DietSeasonEntity season);

    @Query("select count(meal) from MealEntity meal where date(meal.dayTime) = ?1")
    int countByDay(LocalDate day);

    @Query(value = """
            select
            	ds.id as season_id,
            	ds.name as season_name,
            	m.id as mealId,
            	m.description as mealDescription,
            	m.day_time as dayTime,
            	fpm.quantity,
            	f.name as foodName,
            	f.base_quantity as baseQuantity,
            	f.calories,
            	f.carbohydrate,
            	f.fiber,
            	f.protein,
            	f.fat
            from diet_season ds
            join meal m on (ds.id = m.id_diet_season)
            join food_per_meal fpm on (m.id = fpm.id_meal)
            join food f on (fpm.id_food = f.id)
            WHERE ds.active is true	and ds.user_id = :userId
            order by ds.id,	m.id, m.day_time desc
            """, nativeQuery = true)
    List<MealReportProjection> mealReport(Long userId);
}

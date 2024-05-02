package com.fiber.service;

import com.fiber.payload.http.report.FoodReportResponsePayload;
import com.fiber.payload.http.report.MealDaysReportResponsePayload;
import com.fiber.payload.http.report.MealDetailReportResponsePayload;
import com.fiber.payload.http.report.MealReportResponsePayload;
import com.fiber.projection.MealReportProjection;
import com.fiber.repository.MealRepository;
import com.fiber.util.FoodMacroTotalizer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MealReportService {

    private final MealRepository mealRepository;

    public MealReportResponsePayload get(Long userId) {
        List<MealReportProjection> mealReport = mealRepository.mealReport(userId);
        List<MealDaysReportResponsePayload> mealDays = new ArrayList<>();

        for (MealReportProjection projection : mealReport) {
            LocalDate day = projection.getDayTime().toLocalDate();
            LocalTime time = projection.getDayTime().toLocalTime();

            Optional<MealDaysReportResponsePayload> dayFound = mealDays
                    .stream()
                    .filter(md -> md.day().isEqual(day))
                    .findAny();

            if (dayFound.isPresent()) {
                MealDaysReportResponsePayload mealDay = dayFound.get();
                Optional<MealDetailReportResponsePayload> mealDetail = mealDay
                        .meals()
                        .stream()
                        .filter(md -> md.hour().equals(time))
                        .findAny();
                if (mealDetail.isPresent()) {
                    mealDetail.get().foods().add(buildFood(projection));
                } else {
                    List<FoodReportResponsePayload> foods = new ArrayList<>();
                    foods.add(buildFood(projection));
                    MealDetailReportResponsePayload newMealDetail = new MealDetailReportResponsePayload(
                            projection.getMealDescription(), time, foods);
                    mealDay.meals().add(newMealDetail);
                }
            } else {
                List<MealDetailReportResponsePayload> meals = new ArrayList<>();
                meals.add(buildNewMeal(projection));
                MealDaysReportResponsePayload mealDay = new MealDaysReportResponsePayload(day, meals);
                mealDays.add(mealDay);
            }
        }

        return mealReport.isEmpty() ? null : new MealReportResponsePayload(mealReport.get(0).getSeasonId(), mealDays);
    }

    private MealDetailReportResponsePayload buildNewMeal(MealReportProjection projection) {
        List<FoodReportResponsePayload> foods = new ArrayList<>();
        foods.add(buildFood(projection));
        return new MealDetailReportResponsePayload(
                projection.getMealDescription(),
                projection.getDayTime().toLocalTime(),
                foods);
    }

    private FoodReportResponsePayload buildFood(MealReportProjection projection) {
        FoodMacroTotalizer totalizer = new FoodMacroTotalizer(projection, projection.getQuantity());
        return new FoodReportResponsePayload(
                projection.getFoodName(), projection.getQuantity(), totalizer.getKcal(),
                totalizer.getCarbs(), totalizer.getFat(), totalizer.getProtein());
    }

}

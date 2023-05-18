package com.fiber.payload.http.season;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.entity.MealDayEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record MealDayCreateRequestPayload(
        LocalDate day,
        Long idDietSeason,
        List<MealRequestPayload> meals
) {

    public MealDayEntity toEntity(DietSeasonEntity season) {
        List<MealRequestPayload> list = meals() == null ? List.of() : meals();
        return new MealDayEntity(null, day(), season, MealRequestPayload.toEntity(list));
    }

    public static List<MealDayEntity> toEntity(List<MealDayCreateRequestPayload> payloads, DietSeasonEntity season) {
        return payloads.stream().map(p -> p.toEntity(season)).collect(Collectors.toList());
    }
}

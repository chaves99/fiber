package com.fiber.mapper;

import com.fiber.entity.DietSeasonEntity;
import com.fiber.entity.UserEntity;
import com.fiber.payload.http.season.SeasonCreateRequestPayload;
import org.springframework.stereotype.Component;

@Component
public class SeasonMapper {

    public DietSeasonEntity payloadToEntity(UserEntity user, Boolean active, SeasonCreateRequestPayload payload) {
        DietSeasonEntity entity = payload.toEntity(user, active);
//        MealDayCreateRequestPayload.toEntity(payload.mealDays(), entity);
        return entity;
    }

}

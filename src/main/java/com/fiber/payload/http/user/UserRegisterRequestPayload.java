package com.fiber.payload.http.user;

import com.fiber.entity.UnitMeasurementHeight;
import com.fiber.entity.UnitMeasurementWeight;
import com.fiber.entity.UserEntity;

public record UserRegisterRequestPayload(
        String name,
        String email,
        String password,
        Double weight,
        Double height,
        String weightUnit,
        String heightUnit
)  {

    public UserEntity toEntity() {
        return new UserEntity(null, name(), email(),
                password(),weight(), height(),
                UnitMeasurementWeight.getByName(weightUnit()),
                UnitMeasurementHeight.getByName(heightUnit()), null);
    }

}

package com.fiber.payload.http.user;

import com.fiber.entity.UnitMeasurementHeight;
import com.fiber.entity.UnitMeasurementWeight;
import com.fiber.entity.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserRegisterRequestPayload(
        @NotNull @NotEmpty String name,
        @NotNull @NotEmpty String email,
        @NotNull @NotEmpty String password,
        Double weight,
        Double height,
        UnitMeasurementWeight weightUnit,
        UnitMeasurementHeight heightUnit
) {

    public UserEntity toEntity() {
        return new UserEntity(null, name(), email(),
                password(), weight(), height(),
                weightUnit(),
                heightUnit(),
                null);
    }

}

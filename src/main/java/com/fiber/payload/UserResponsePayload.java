package com.fiber.payload;

import com.fiber.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public record UserResponsePayload(Long id, String name, String email,
                                  Double weight, Double height,
                                  String weightUnit, String heightUnit) {

    public static UserResponsePayload from(UserEntity entity) {
        return new UserResponsePayload(entity.getId(), entity.getName(),
                entity.getEmail(), entity.getWeight(), entity.getHeight(),
                entity.getWeightUnit().name(), entity.getHeightUnit().name());
    }

    public static List<UserResponsePayload> from(List<UserEntity> entities) {
        return entities.stream()
                .map(e -> from(e))
                .collect(Collectors.toList());

    }
}

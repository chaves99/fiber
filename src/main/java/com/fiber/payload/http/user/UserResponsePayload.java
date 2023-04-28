package com.fiber.payload.http.user;

import com.fiber.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public record UserResponsePayload(Long id, String name, String email,
                                  Double weight, Double height,
                                  String weightUnit, String heightUnit) {

    public static UserResponsePayload from(UserEntity entity) {
        String weightUnit = entity.getWeightUnit() != null ? entity.getWeightUnit().name() : "";
        String heightUnit = entity.getHeightUnit() != null ? entity.getHeightUnit().name() : "";
        Double height = entity.getHeight() != null ? entity.getHeight() : 0;
        Double weight = entity.getWeight() != null ? entity.getWeight() : 0;
        return new UserResponsePayload(entity.getId(), entity.getName(),
                entity.getEmail(), weight, height,
                weightUnit, heightUnit);
    }

    public static List<UserResponsePayload> from(List<UserEntity> entities) {
        return entities.stream()
                .map(e -> from(e))
                .collect(Collectors.toList());

    }
}

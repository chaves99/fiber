package com.fiber.util;

import com.fiber.entity.DietSeasonEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ListsUtil {

    public static boolean containSomething(List<DietSeasonEntity> seasonEntities) {
        return seasonEntities == null || seasonEntities.isEmpty();
    }

}

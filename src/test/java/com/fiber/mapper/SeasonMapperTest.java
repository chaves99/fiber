package com.fiber.mapper;

import com.fiber.payload.http.season.MealDayCreateRequestPayload;
import com.fiber.payload.http.season.SeasonCreateRequestPayload;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class SeasonMapperTest {

    @Test
    public void testMapper() {
        LocalDate now = LocalDate.now();
        SeasonCreateRequestPayload payload =
                new SeasonCreateRequestPayload("bulking", "bulking",
                        3500d, 500d, 200d, 100d, now, null,
                        1L);
    }

}

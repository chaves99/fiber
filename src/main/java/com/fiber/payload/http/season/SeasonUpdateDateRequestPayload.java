package com.fiber.payload.http.season;

import java.time.LocalDate;

public record SeasonUpdateDateRequestPayload(
        LocalDate finalDate
) {
}

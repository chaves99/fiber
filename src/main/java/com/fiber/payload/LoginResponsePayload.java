package com.fiber.payload;

public record LoginResponsePayload(Long id, String name, String email,
                                   String token) {

}

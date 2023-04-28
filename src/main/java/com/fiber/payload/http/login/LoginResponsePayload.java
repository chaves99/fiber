package com.fiber.payload.http.login;

public record LoginResponsePayload(Long id, String name, String email,
                                   String token) {

}

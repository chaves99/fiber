package com.fiber.service;

import com.fiber.entity.UserEntity;
import com.fiber.payload.LoginRequestPayload;
import com.fiber.payload.LoginResponsePayload;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class LoginService {

    private AuthenticationManager authenticationManager;

    private TokenService tokenService;

    public LoginResponsePayload login(LoginRequestPayload loginRequestPayload) {
        try {
            Authentication authentication = authenticationManager.authenticate(loginRequestPayload);
            String token = tokenService.generate(authentication);
            if (authentication.getDetails() instanceof UserEntity user) {
                LoginResponsePayload response = new LoginResponsePayload(user.getId(), user.getName(), user.getEmail(), token);
                return response;
            }
            return new LoginResponsePayload(null, authentication.getName(), null, token);
        } catch (AuthenticationException e) {
            log.error("login - username:{}", loginRequestPayload.getName());
            log.error("login - Exception: ", e);
            throw e;
        }
    }
}

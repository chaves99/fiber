package com.fiber.service;

import com.fiber.entity.UserEntity;
import com.fiber.model.UserAuthenticationModel;
import com.fiber.payload.http.login.LoginRequestPayload;
import com.fiber.payload.http.login.LoginResponsePayload;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@AllArgsConstructor
public class LoginService {

    private AuthenticationManager authenticationManager;

    private TokenService tokenService;

    public LoginResponsePayload login(LoginRequestPayload loginRequestPayload) {
        try {
            Authentication authentication = authenticationManager.authenticate(getAuthentication(loginRequestPayload));
            String token = tokenService.generate(authentication);
            if (authentication.getDetails() instanceof UserEntity user) {
                return new LoginResponsePayload(user.getId(), user.getName(), user.getEmail(), token);
            }
            return new LoginResponsePayload(null, authentication.getName(), null, token);
        } catch (AuthenticationException e) {
            log.error("login - username:{}", loginRequestPayload.username());
            log.error("login - Exception: ", e);
            throw e;
        }
    }

    private Authentication getAuthentication(LoginRequestPayload loginRequestPayload) {
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return loginRequestPayload.password();
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return loginRequestPayload.username();
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return loginRequestPayload.username();
            }
        };
    }
}

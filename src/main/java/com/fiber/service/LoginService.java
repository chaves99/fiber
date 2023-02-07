package com.fiber.service;

import com.fiber.payload.LoginRequestPayload;
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

    public Authentication login(LoginRequestPayload loginRequestPayload) {
        try {
            return authenticationManager.authenticate(loginRequestPayload);
        } catch (AuthenticationException e){
            log.error("login - username:{}", loginRequestPayload.getName());
            log.error("login - Exception: ", e);
            return new Authentication() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return null;
                }

                @Override
                public Object getCredentials() {
                    return null;
                }

                @Override
                public Object getDetails() {
                    return null;
                }

                @Override
                public Object getPrincipal() {
                    return null;
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
                    return null;
                }
            };
        }
    }
}

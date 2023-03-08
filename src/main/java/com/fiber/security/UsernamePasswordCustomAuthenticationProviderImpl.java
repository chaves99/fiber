package com.fiber.security;

import com.fiber.payload.LoginRequestPayload;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

@Slf4j
public class UsernamePasswordCustomAuthenticationProviderImpl implements AuthenticationProvider {

    @Setter
    private UserDetailsService userDetailsService;

    @Setter
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("authenticate - username:{}", authentication.getName());
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        boolean matches = passwordEncoder.matches((String) authentication.getCredentials(), userDetails.getPassword());
        if (matches) {
            return new Authentication() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return AuthorityUtils.NO_AUTHORITIES;
                }

                @Override
                public Object getCredentials() {
                    return userDetails.getPassword();
                }

                @Override
                public Object getDetails() {
                    return userDetails;
                }

                @Override
                public Object getPrincipal() {
                    return null;
                }

                @Override
                public boolean isAuthenticated() {
                    return true;
                }

                @Override
                public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

                }

                @Override
                public String getName() {
                    return userDetails.getUsername();
                }
            };
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(LoginRequestPayload.class);
    }
}

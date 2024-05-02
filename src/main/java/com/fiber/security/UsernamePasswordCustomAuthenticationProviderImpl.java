package com.fiber.security;

import com.fiber.model.UserAuthenticationModel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Setter
@Slf4j
public class UsernamePasswordCustomAuthenticationProviderImpl implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("authenticate - username:{}", authentication.getName());
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        boolean matches = passwordEncoder.matches((String) authentication.getCredentials(), userDetails.getPassword());
        if (matches) {
            return new UserAuthenticationModel(userDetails);
        }
        throw new AuthenticationException("Error on login"){};
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}

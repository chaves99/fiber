package com.fiber.config;

import com.fiber.payload.LoginRequestPayload;
import com.fiber.repository.UserRepository;
import com.fiber.security.UsernamePasswordCustomAuthenticationProviderImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;

@Slf4j
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authorizeHttpRequests(r -> {
                    r.requestMatchers("/status", "/user/login", "/user/test").permitAll();
                    r.anyRequest().authenticated();
                })
                .httpBasic().and()
                .formLogin().disable()
                .cors().disable()
                .csrf().disable()
                .headers().frameOptions().disable().and()
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        return new ProviderManager(new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
                boolean matches = passwordEncoder().matches((String) authentication.getCredentials(), userDetails.getPassword());
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
        });
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            return userRepository.findByName(username).orElseThrow(() -> {
                throw new UsernameNotFoundException("Not found user: " + username);
            });
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

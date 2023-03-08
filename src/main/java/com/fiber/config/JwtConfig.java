package com.fiber.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.spec.SecretKeySpec;

@Configuration
public class JwtConfig {

    @Value("${jwt.key}")
    private String jwtKey;

    @Bean
    JwtDecoder jwtDecoder() {
        byte[] keyBytes = jwtKey.getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, 0, keyBytes.length, "RSA");
        return NimbusJwtDecoder.withSecretKey(keySpec).macAlgorithm(algorithm()).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(jwtKey.getBytes()));
    }

    @Bean
    MacAlgorithm algorithm() {
        return MacAlgorithm.HS512;
    }

}

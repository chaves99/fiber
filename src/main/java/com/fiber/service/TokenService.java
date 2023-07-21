package com.fiber.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Value("${jwt.expiretime}")
    private String expireAt;

    private JwtEncoder encoder;

    private MacAlgorithm algorithm;

    public TokenService(JwtEncoder encoder, MacAlgorithm algorithm) {
        this.encoder = encoder;
        this.algorithm = algorithm;
    }

    public String generate(Authentication authentication) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(authentication.getName())
                .issuedAt(now)
                .expiresAt(now.plus(Long.parseLong(expireAt), ChronoUnit.MINUTES))
                .subject(authentication.getName())
                .claim("scope", "No auth")
                .build();

        return encoder
                .encode(JwtEncoderParameters.from(JwsHeader.with(algorithm).build(), claims))
                .getTokenValue();
    }
}

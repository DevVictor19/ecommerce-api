package com.devvictor.ecommerce_api.user.infra.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.devvictor.ecommerce_api.shared.application.providers.EnvConfigProvider;
import com.devvictor.ecommerce_api.user.application.providers.JwtProvider;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class JwtProviderImpl implements JwtProvider {
    private final Algorithm algorithm;
    private final String issuerUrl;

    public JwtProviderImpl(EnvConfigProvider envConfigProvider) {
        algorithm = Algorithm.HMAC256(envConfigProvider.getServerSecret());
        issuerUrl = envConfigProvider.getServerUrl();
    }

    @Override
    public String generateToken(String userId) {
        // 8 hours
        final Instant expiration = LocalDateTime
                .now().plusHours(8)
                .toInstant(ZoneOffset.of("-03:00"));

        try {
            return JWT.create()
                    .withIssuer(issuerUrl)
                    .withSubject(userId)
                    .withExpiresAt(expiration)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token");
        }
    }

    @Override
    public String validateToken(String token) {
        try {
            return JWT.require(algorithm)
                    .withIssuer(issuerUrl)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Error while validating token");
        }
    }
}

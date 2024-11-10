package com.devvictor.ecommerce_api.user.infra.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.devvictor.ecommerce_api.shared.application.providers.EnvConfigProvider;
import com.devvictor.ecommerce_api.user.application.jwt.JwtPayload;
import com.devvictor.ecommerce_api.user.application.providers.JwtProvider;
import com.devvictor.ecommerce_api.user.domain.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;

@Component
public class JwtProviderImpl implements JwtProvider {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Algorithm algorithm;
    private final String issuerUrl;

    public JwtProviderImpl(EnvConfigProvider envConfigProvider) {
        algorithm = Algorithm.HMAC256(envConfigProvider.getServerSecret());
        issuerUrl = envConfigProvider.getServerUrl();
    }

    @Override
    public String generateToken(User user) {
        // 8 hours
        final Instant expiration = LocalDateTime
                .now().plusHours(8)
                .toInstant(ZoneOffset.of("-03:00"));

        try {
            String payload = objectMapper.writeValueAsString(
                    new JwtPayload(user.getId(), user.getRoles())
            );

            return JWT.create()
                    .withIssuer(issuerUrl)
                    .withPayload(payload)
                    .withExpiresAt(expiration)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token");
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while parsing jwt payload");
        }
    }

    @Override
    public JwtPayload validateToken(String token) {
        try {
            String payload = JWT.require(algorithm)
                    .withIssuer(issuerUrl)
                    .build()
                    .verify(token)
                    .getPayload();

            String decodedPayload = new String(Base64.getUrlDecoder().decode(payload));

            return objectMapper.readValue(decodedPayload, JwtPayload.class);
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Error while validating token");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.devvictor.ecommerce_api.application.providers;

public interface JwtProvider {
    String generateToken(String userId);
    String validateToken(String token);
}

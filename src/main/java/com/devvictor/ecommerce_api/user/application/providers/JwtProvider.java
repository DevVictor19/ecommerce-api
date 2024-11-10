package com.devvictor.ecommerce_api.user.application.providers;

import com.devvictor.ecommerce_api.user.application.jwt.JwtPayload;
import com.devvictor.ecommerce_api.user.domain.entities.User;

public interface JwtProvider {
    String generateToken(User user);
    JwtPayload validateToken(String token);
}

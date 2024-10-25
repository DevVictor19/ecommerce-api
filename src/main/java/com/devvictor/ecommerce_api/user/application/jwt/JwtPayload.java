package com.devvictor.ecommerce_api.user.application.jwt;

import com.devvictor.ecommerce_api.user.domain.enums.Role;

import java.util.List;

public record JwtPayload(
        String userId,
        List<Role> roles
) {
}

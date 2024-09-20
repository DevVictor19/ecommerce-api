package com.devvictor.ecommerce_api.domain.factories;

import com.devvictor.ecommerce_api.domain.entities.User;

import java.util.Date;
import java.util.UUID;

public class UserFactory {
    static User create(String username, String email, String password) {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .username(username)
                .email(email)
                .password(password)
                .createdAt(new Date())
                .build();
    }
}

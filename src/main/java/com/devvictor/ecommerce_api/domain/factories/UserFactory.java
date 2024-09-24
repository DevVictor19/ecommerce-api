package com.devvictor.ecommerce_api.domain.factories;

import com.devvictor.ecommerce_api.domain.entities.User;
import com.devvictor.ecommerce_api.domain.enums.Role;

import java.util.*;

public class UserFactory {
    public static User create(String username, String email, String password) {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .username(username)
                .email(email)
                .password(password)
                .roles(List.of(Role.CLIENT))
                .createdAt(new Date())
                .build();
    }

    public static User create(String username, String email, String password, List<Role> roles) {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .username(username)
                .email(email)
                .password(password)
                .roles(roles)
                .createdAt(new Date())
                .build();
    }
}

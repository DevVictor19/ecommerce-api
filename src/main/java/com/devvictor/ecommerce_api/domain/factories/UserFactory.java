package com.devvictor.ecommerce_api.domain.factories;

import com.devvictor.ecommerce_api.domain.entities.User;
import com.devvictor.ecommerce_api.domain.enums.Role;

import java.util.*;

public class UserFactory {
    public static User create(String username, String email, String password) {
        var entity = new User();
        entity.setId(UUID.randomUUID().toString());
        entity.setUsername(username);
        entity.setEmail(email);
        entity.setPassword(password);
        entity.setRoles(List.of(Role.CLIENT));
        entity.setCreatedAt(new Date());
        return entity;
    }

    public static User create(String username, String email, String password, List<Role> roles) {
        var entity = new User();
        entity.setId(UUID.randomUUID().toString());
        entity.setUsername(username);
        entity.setEmail(email);
        entity.setPassword(password);
        entity.setRoles(roles);
        entity.setCreatedAt(new Date());
        return entity;
    }
}

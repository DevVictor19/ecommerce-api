package com.devvictor.ecommerce_api.user.application.jwt;

import com.devvictor.ecommerce_api.user.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JwtPayload {
    private String userId;
    private List<Role> roles;
}
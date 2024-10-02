package com.devvictor.ecommerce_api.application.use_cases.users;

import com.devvictor.ecommerce_api.application.exceptions.UnauthorizedException;
import com.devvictor.ecommerce_api.application.providers.HashProvider;
import com.devvictor.ecommerce_api.application.providers.JwtProvider;
import com.devvictor.ecommerce_api.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUserUseCase {
    private final UserService userService;
    private final HashProvider hashProvider;
    private final JwtProvider jwtProvider;

    public String execute(String email, String password) {
        var existingUser = userService.findByEmail(email);

        if (existingUser.isEmpty()) {
            throw new UnauthorizedException("Invalid email or password");
        }

        boolean isValidPassword = hashProvider.compare(
                password,
                existingUser.get().getPassword()
        );

        if (!isValidPassword) {
            throw new UnauthorizedException("Invalid email or password");
        }

        return jwtProvider.generateToken(existingUser.get().getId());
    }
}

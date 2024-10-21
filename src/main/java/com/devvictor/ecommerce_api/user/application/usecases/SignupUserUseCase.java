package com.devvictor.ecommerce_api.user.application.usecases;

import com.devvictor.ecommerce_api.shared.application.exceptions.BadRequestException;
import com.devvictor.ecommerce_api.user.application.providers.HashProvider;
import com.devvictor.ecommerce_api.user.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignupUserUseCase {
    private final UserService userService;
    private final HashProvider hashProvider;

    public void execute(String username, String email, String password) {
       var existingUser = userService.findByEmail(email);

       if (existingUser.isPresent()) {
           throw new BadRequestException("Email already in use");
       }

       String hashedPwd = hashProvider.hash(password);

       userService.create(username, email, hashedPwd);
    }
}

package com.devvictor.ecommerce_api.application.use_cases.users;

import com.devvictor.ecommerce_api.application.dtos.users.SignupUserInputDTO;
import com.devvictor.ecommerce_api.application.exceptions.BadRequestException;
import com.devvictor.ecommerce_api.application.providers.HashProvider;
import com.devvictor.ecommerce_api.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignupUserUseCase {
    private final UserService userService;
    private final HashProvider hashProvider;

    public void execute(SignupUserInputDTO dto) {
       var existingUser = userService.findByEmail(dto.email());

       if (existingUser.isPresent()) {
           throw new BadRequestException("Email already in use");
       }

       String hashedPwd = hashProvider.hash(dto.password());

       userService.create(dto.username(), dto.email(), hashedPwd);
    }
}

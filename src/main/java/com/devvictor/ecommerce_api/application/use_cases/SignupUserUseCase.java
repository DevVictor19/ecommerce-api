package com.devvictor.ecommerce_api.application.use_cases;

import com.devvictor.ecommerce_api.application.dtos.SignupUserRequestDTO;
import com.devvictor.ecommerce_api.application.exceptions.BadRequestException;
import com.devvictor.ecommerce_api.application.providers.HashProvider;
import com.devvictor.ecommerce_api.domain.entities.User;
import com.devvictor.ecommerce_api.domain.factories.UserFactory;
import com.devvictor.ecommerce_api.domain.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class SignupUserUseCase {
    private final UserRepository userRepository;
    private final HashProvider hashProvider;

    public SignupUserUseCase(UserRepository userRepository,
                             HashProvider hashProvider) {
        this.userRepository = userRepository;
        this.hashProvider = hashProvider;
    }

    public void execute(SignupUserRequestDTO dto) {
       var existingUser = userRepository.findByEmail(dto.email());

       if (existingUser.isPresent()) {
           throw new BadRequestException("Email already in use");
       }

        User user = UserFactory.create(
                dto.username(),
                dto.email(),
                hashProvider.hash(dto.password())
        );

       userRepository.insert(user);
    }
}

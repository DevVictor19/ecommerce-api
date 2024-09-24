package com.devvictor.ecommerce_api.application.use_cases;

import com.devvictor.ecommerce_api.application.dtos.LoginUserRequestDTO;
import com.devvictor.ecommerce_api.application.dtos.LoginUserResponseDTO;
import com.devvictor.ecommerce_api.application.exceptions.UnauthorizedException;
import com.devvictor.ecommerce_api.application.providers.HashProvider;
import com.devvictor.ecommerce_api.application.providers.JwtProvider;
import com.devvictor.ecommerce_api.domain.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class LoginUserUseCase {
    private final UserRepository userRepository;
    private final HashProvider hashProvider;
    private final JwtProvider jwtProvider;

    public LoginUserUseCase(UserRepository userRepository,
                            HashProvider hashProvider,
                            JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.hashProvider = hashProvider;
        this.jwtProvider = jwtProvider;
    }

    public LoginUserResponseDTO execute(LoginUserRequestDTO dto) {
        var existingUser = userRepository.findByEmail(dto.email());

        if (existingUser.isEmpty()) {
            throw new UnauthorizedException("Invalid email or password");
        }

        boolean isValidPassword = hashProvider.compare(dto.password(),
                existingUser.get().getPassword());

        if (!isValidPassword) {
            throw new UnauthorizedException("Invalid email or password");
        }

        String token = jwtProvider.generateToken(existingUser.get().getId());

        return new LoginUserResponseDTO(token);
    }
}

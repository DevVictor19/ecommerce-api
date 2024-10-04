package com.devvictor.ecommerce_api.infra.controllers;

import com.devvictor.ecommerce_api.application.use_cases.users.LoginUserUseCase;
import com.devvictor.ecommerce_api.application.use_cases.users.SignupUserUseCase;
import com.devvictor.ecommerce_api.infra.dtos.users.LoginUserDTO;
import com.devvictor.ecommerce_api.infra.dtos.users.SignupUserDTO;
import com.devvictor.ecommerce_api.infra.dtos.users.TokenDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final SignupUserUseCase signupUserUseCase;
    private final LoginUserUseCase loginUserUseCase;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupUserDTO dto) {
        signupUserUseCase.execute(dto.username(), dto.email(), dto.password());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginUserDTO dto) {
        String token = loginUserUseCase.execute(dto.email(), dto.password());

        return ResponseEntity.ok(new TokenDTO(token));
    }
}

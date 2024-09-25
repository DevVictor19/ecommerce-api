package com.devvictor.ecommerce_api.infra.controllers;

import com.devvictor.ecommerce_api.application.dtos.LoginUserRequestDTO;
import com.devvictor.ecommerce_api.application.dtos.LoginUserResponseDTO;
import com.devvictor.ecommerce_api.application.dtos.SignupUserRequestDTO;
import com.devvictor.ecommerce_api.application.use_cases.LoginUserUseCase;
import com.devvictor.ecommerce_api.application.use_cases.SignupUserUseCase;
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
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupUserRequestDTO dto) {
        signupUserUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDTO> login(@Valid @RequestBody LoginUserRequestDTO dto) {

        return ResponseEntity.ok(loginUserUseCase.execute(dto));
    }
}

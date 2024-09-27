package com.devvictor.ecommerce_api.infra.controllers;

import com.devvictor.ecommerce_api.application.dtos.input.LoginUserInputDTO;
import com.devvictor.ecommerce_api.application.dtos.input.SignupUserInputDTO;
import com.devvictor.ecommerce_api.application.dtos.output.LoginUserOutputDTO;
import com.devvictor.ecommerce_api.application.use_cases.LoginUserUseCase;
import com.devvictor.ecommerce_api.application.use_cases.SignupUserUseCase;
import com.devvictor.ecommerce_api.infra.contracts.request.LoginUserRequest;
import com.devvictor.ecommerce_api.infra.contracts.request.SignupUserRequest;
import com.devvictor.ecommerce_api.infra.contracts.response.LoginUserResponse;
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
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupUserRequest body) {
        SignupUserInputDTO input = new SignupUserInputDTO(
                body.username(),
                body.email(),
                body.password()
        );

        signupUserUseCase.execute(input);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login(@Valid @RequestBody LoginUserRequest body) {
        LoginUserInputDTO input = new LoginUserInputDTO(
                body.email(),
                body.password()
        );

        LoginUserOutputDTO output = loginUserUseCase.execute(input);

        LoginUserResponse response = new LoginUserResponse(output.token());

        return ResponseEntity.ok(response);
    }
}

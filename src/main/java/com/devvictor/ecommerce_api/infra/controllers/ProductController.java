package com.devvictor.ecommerce_api.infra.controllers;

import com.devvictor.ecommerce_api.application.dtos.CreateProductRequestDTO;
import com.devvictor.ecommerce_api.application.use_cases.CreateProductUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final CreateProductUseCase createProductUseCase;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateProductRequestDTO dto) {
        createProductUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

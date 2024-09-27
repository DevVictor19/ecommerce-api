package com.devvictor.ecommerce_api.infra.controllers;

import com.devvictor.ecommerce_api.application.dtos.CreateProductRequestDTO;
import com.devvictor.ecommerce_api.application.dtos.UpdateProductRequestDTO;
import com.devvictor.ecommerce_api.application.use_cases.CreateProductUseCase;
import com.devvictor.ecommerce_api.application.use_cases.DeleteProductUseCase;
import com.devvictor.ecommerce_api.application.use_cases.UpdateProductUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateProductRequestDTO dto) {
        createProductUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id,
                                       @Valid @RequestBody UpdateProductRequestDTO dto) {
        updateProductUseCase.execute(id.toString(), dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteProductUseCase.execute(id.toString());

        return ResponseEntity.ok().build();
    }
}

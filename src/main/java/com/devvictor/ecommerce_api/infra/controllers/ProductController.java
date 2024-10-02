package com.devvictor.ecommerce_api.infra.controllers;

import com.devvictor.ecommerce_api.application.use_cases.products.CreateProductUseCase;
import com.devvictor.ecommerce_api.application.use_cases.products.DeleteProductUseCase;
import com.devvictor.ecommerce_api.application.use_cases.products.FindAllProductsUseCase;
import com.devvictor.ecommerce_api.application.use_cases.products.UpdateProductUseCase;
import com.devvictor.ecommerce_api.infra.dtos.products.CreateProductDTO;
import com.devvictor.ecommerce_api.infra.dtos.products.ProductDTO;
import com.devvictor.ecommerce_api.infra.dtos.products.UpdateProductDTO;
import com.devvictor.ecommerce_api.infra.mappers.ProductEntityMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final FindAllProductsUseCase findAllProductsUseCase;
    private final CreateProductUseCase createProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final ProductEntityMapper productEntityMapper;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam int page,
            @RequestParam int size
    ) {

        return ResponseEntity.ok(findAllProductsUseCase
                .execute(name, page, size)
                .map(productEntityMapper::toDto)
        );
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateProductDTO dto) {
        createProductUseCase.execute(
                dto.price(),
                dto.name(),
                dto.description(),
                dto.photoUrl(),
                dto.stockQuantity()
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id,
                                       @Valid @RequestBody UpdateProductDTO dto) {

        updateProductUseCase.execute(
                id.toString(),
                dto.price(),
                dto.name(),
                dto.description(),
                dto.photoUrl(),
                dto.stockQuantity()
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteProductUseCase.execute(id.toString());

        return ResponseEntity.ok().build();
    }
}

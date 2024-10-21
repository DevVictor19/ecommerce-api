package com.devvictor.ecommerce_api.products.infra.controllers;

import com.devvictor.ecommerce_api.products.application.usecases.CreateProductUseCase;
import com.devvictor.ecommerce_api.products.application.usecases.DeleteProductUseCase;
import com.devvictor.ecommerce_api.products.application.usecases.FindAllProductsUseCase;
import com.devvictor.ecommerce_api.products.application.usecases.UpdateProductUseCase;
import com.devvictor.ecommerce_api.products.infra.dtos.CreateProductDTO;
import com.devvictor.ecommerce_api.products.infra.dtos.ProductDTO;
import com.devvictor.ecommerce_api.products.infra.dtos.UpdateProductDTO;
import com.devvictor.ecommerce_api.products.infra.mappers.ProductEntityMapper;
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
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") String sort,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(required = false) String name) {

        return ResponseEntity.ok(findAllProductsUseCase
                .execute(page, size, sort, sortBy, name)
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

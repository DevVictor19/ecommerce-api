package com.devvictor.ecommerce_api.infra.controllers;

import com.devvictor.ecommerce_api.application.dtos.input.CreateProductInputDTO;
import com.devvictor.ecommerce_api.application.dtos.input.DeleteProductInputDTO;
import com.devvictor.ecommerce_api.application.dtos.input.FindAllProductsInputDTO;
import com.devvictor.ecommerce_api.application.dtos.input.UpdateProductInputDTO;
import com.devvictor.ecommerce_api.application.use_cases.CreateProductUseCase;
import com.devvictor.ecommerce_api.application.use_cases.DeleteProductUseCase;
import com.devvictor.ecommerce_api.application.use_cases.FindAllProductsUseCase;
import com.devvictor.ecommerce_api.application.use_cases.UpdateProductUseCase;
import com.devvictor.ecommerce_api.domain.entities.Product;
import com.devvictor.ecommerce_api.infra.contracts.request.CreateProductRequest;
import com.devvictor.ecommerce_api.infra.contracts.request.UpdateProductRequest;
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

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam int page,
            @RequestParam int size
    ) {
        FindAllProductsInputDTO input = new FindAllProductsInputDTO(name, page, size);

        return ResponseEntity.ok(findAllProductsUseCase.execute(input));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateProductRequest body) {
        CreateProductInputDTO input = new CreateProductInputDTO(
                body.price(),
                body.name(),
                body.description(),
                body.photoUrl(),
                body.stockQuantity()
        );

        createProductUseCase.execute(input);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id,
                                       @Valid @RequestBody UpdateProductRequest body) {

        UpdateProductInputDTO input = new UpdateProductInputDTO(
                id.toString(),
                body.price(),
                body.name(),
                body.description(),
                body.photoUrl(),
                body.stockQuantity()
        );

        updateProductUseCase.execute(input);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        DeleteProductInputDTO input = new DeleteProductInputDTO(id.toString());

        deleteProductUseCase.execute(input);

        return ResponseEntity.ok().build();
    }
}

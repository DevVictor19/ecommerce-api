package com.devvictor.ecommerce_api.infra.controllers;

import com.devvictor.ecommerce_api.application.dtos.input.AddProductToCartInputDTO;
import com.devvictor.ecommerce_api.application.dtos.input.SubtractProductFromCartInputDTO;
import com.devvictor.ecommerce_api.application.exceptions.InternalServerErrorException;
import com.devvictor.ecommerce_api.application.use_cases.AddProductToCartUseCase;
import com.devvictor.ecommerce_api.application.use_cases.SubtractProductFromCartUseCase;
import com.devvictor.ecommerce_api.domain.entities.User;
import com.devvictor.ecommerce_api.infra.contracts.request.AddProductToCartRequest;
import com.devvictor.ecommerce_api.infra.contracts.request.SubtractProductFromCartRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {
    private final AddProductToCartUseCase addProductToCartUseCase;
    private final SubtractProductFromCartUseCase subtractProductFromCartUseCase;

    @PostMapping("/user/products/add")
    public ResponseEntity<Void> addProductToCart(@Valid @RequestBody AddProductToCartRequest body) {
        var user = (Optional<User>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.isEmpty() ) {
           throw new InternalServerErrorException();
        }

        var input = new AddProductToCartInputDTO(
               user.get().getId(),
               body.productId(),
               body.quantity()
        );

        addProductToCartUseCase.execute(input);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/products/subtract")
    public ResponseEntity<Void> subtractProduct(@Valid @RequestBody SubtractProductFromCartRequest body) {
        var user = (Optional<User>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.isEmpty()) {
            throw new InternalServerErrorException();
        }

        var input = new SubtractProductFromCartInputDTO(
                user.get().getId(),
                body.productId(),
                body.quantity()
        );

        subtractProductFromCartUseCase.execute(input);

        return ResponseEntity.ok().build();
    }
}

package com.devvictor.ecommerce_api.infra.controllers;

import com.devvictor.ecommerce_api.application.dtos.carts.AddProductToCartInputDTO;
import com.devvictor.ecommerce_api.application.dtos.carts.ClearCartInputDTO;
import com.devvictor.ecommerce_api.application.dtos.carts.FindCartByUserInputDTO;
import com.devvictor.ecommerce_api.application.dtos.carts.SubtractProductFromCartInputDTO;
import com.devvictor.ecommerce_api.application.dtos.carts.CartOutputDTO;
import com.devvictor.ecommerce_api.application.exceptions.InternalServerErrorException;
import com.devvictor.ecommerce_api.application.use_cases.carts.AddProductToCartUseCase;
import com.devvictor.ecommerce_api.application.use_cases.carts.ClearCartUseCase;
import com.devvictor.ecommerce_api.application.use_cases.carts.FindCartByUserUseCase;
import com.devvictor.ecommerce_api.application.use_cases.carts.SubtractProductFromCartUseCase;
import com.devvictor.ecommerce_api.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {
    private final FindCartByUserUseCase findCartByUserUseCase;
    private final ClearCartUseCase clearCartUseCase;
    private final AddProductToCartUseCase addProductToCartUseCase;
    private final SubtractProductFromCartUseCase subtractProductFromCartUseCase;

    @GetMapping("/my-cart")
    public ResponseEntity<CartOutputDTO> findCartByUser() {
        var user = (Optional<User>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.isEmpty() ) {
            throw new InternalServerErrorException();
        }

        var input = new FindCartByUserInputDTO(user.get().getId());

        return ResponseEntity.ok(findCartByUserUseCase.execute(input));
    }

    @DeleteMapping("/my-cart")
    public ResponseEntity<Void> clearCart() {
        var user = (Optional<User>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.isEmpty()) {
            throw new InternalServerErrorException();
        }

        var input = new ClearCartInputDTO(user.get().getId());

        clearCartUseCase.execute(input);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/my-cart/products/{productId}")
    public ResponseEntity<Void> addProductToCart(@PathVariable UUID productId,
                                                 @RequestParam int quantity) {

        var user = (Optional<User>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.isEmpty() ) {
           throw new InternalServerErrorException();
        }

        var input = new AddProductToCartInputDTO(
               user.get().getId(),
               productId.toString(),
               quantity
        );

        addProductToCartUseCase.execute(input);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/my-cart/products/{productId}")
    public ResponseEntity<Void> subtractProduct(@PathVariable UUID productId,
                                                @RequestParam int quantity) {

        var user = (Optional<User>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.isEmpty()) {
            throw new InternalServerErrorException();
        }

        var input = new SubtractProductFromCartInputDTO(
                user.get().getId(),
                productId.toString(),
                quantity
        );

        subtractProductFromCartUseCase.execute(input);

        return ResponseEntity.ok().build();
    }
}

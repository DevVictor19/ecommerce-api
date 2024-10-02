package com.devvictor.ecommerce_api.infra.controllers;

import com.devvictor.ecommerce_api.application.exceptions.InternalServerErrorException;
import com.devvictor.ecommerce_api.application.use_cases.carts.AddProductToCartUseCase;
import com.devvictor.ecommerce_api.application.use_cases.carts.ClearCartUseCase;
import com.devvictor.ecommerce_api.application.use_cases.carts.FindCartByUserUseCase;
import com.devvictor.ecommerce_api.application.use_cases.carts.SubtractProductFromCartUseCase;
import com.devvictor.ecommerce_api.domain.entities.Cart;
import com.devvictor.ecommerce_api.domain.entities.User;
import com.devvictor.ecommerce_api.infra.dtos.carts.CartDTO;
import com.devvictor.ecommerce_api.infra.mappers.CartEntityMapper;
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
    private final CartEntityMapper cartEntityMapper;

    @GetMapping("/my-cart")
    public ResponseEntity<CartDTO> findCartByUser() {
        Cart output = findCartByUserUseCase.execute(getAuthenticatedUser().getId());

        return ResponseEntity.ok(cartEntityMapper.toDto(output));
    }

    @DeleteMapping("/my-cart")
    public ResponseEntity<Void> clearCart() {
        clearCartUseCase.execute(getAuthenticatedUser().getId());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/my-cart/products/{productId}")
    public ResponseEntity<Void> addProductToCart(@PathVariable UUID productId,
                                                 @RequestParam int quantity) {

        addProductToCartUseCase.execute(productId.toString(), getAuthenticatedUser().getId(), quantity);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/my-cart/products/{productId}")
    public ResponseEntity<Void> subtractProduct(@PathVariable UUID productId,
                                                @RequestParam int quantity) {

        subtractProductFromCartUseCase.execute(productId.toString(), getAuthenticatedUser().getId(), quantity);

        return ResponseEntity.ok().build();
    }

    private User getAuthenticatedUser() {
        Optional<User> user = (Optional<User>) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (user.isEmpty() ) {
            throw new InternalServerErrorException();
        }

        return user.get();
    }
}

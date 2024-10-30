package com.devvictor.ecommerce_api.cart.application.usecases;

import com.devvictor.ecommerce_api.cart.application.services.CartService;
import com.devvictor.ecommerce_api.cart.domain.entities.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindCartByUserUseCase {
    private final CartService cartService;

    public Optional<Cart> execute(String userId) {
       return cartService.findByUserId(userId);
    }
}

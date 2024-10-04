package com.devvictor.ecommerce_api.application.use_cases.carts;

import com.devvictor.ecommerce_api.application.exceptions.NotFoundException;
import com.devvictor.ecommerce_api.application.services.CartService;
import com.devvictor.ecommerce_api.domain.entities.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClearCartUseCase {
    private final CartService cartService;

    public void execute(String userId) {
        Optional<Cart> cart = cartService.findByUserId(userId);

        if (cart.isEmpty()) {
            throw new NotFoundException("Cart not found");
        }

        Cart existentCart = cart.get();

        cartService.delete(existentCart);
    }
}

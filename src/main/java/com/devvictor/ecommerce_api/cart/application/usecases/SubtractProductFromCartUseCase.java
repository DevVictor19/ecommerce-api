package com.devvictor.ecommerce_api.cart.application.usecases;

import com.devvictor.ecommerce_api.shared.application.exceptions.NotFoundException;
import com.devvictor.ecommerce_api.cart.application.services.CartService;
import com.devvictor.ecommerce_api.cart.domain.entities.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SubtractProductFromCartUseCase {
    private final CartService cartService;

    public void execute(String productId, String userId, int quantity) {
        Optional<Cart> cart = cartService.findByUserId(userId);

        if (cart.isEmpty()) {
            throw new NotFoundException("Cart not found");
        }

        Cart existentCart = cart.get();

        existentCart.subtractProduct(productId, quantity);

        if (existentCart.getProductsQuantity() == 0) {
            cartService.delete(existentCart);
            return;
        }

        cartService.update(existentCart);
    }
}

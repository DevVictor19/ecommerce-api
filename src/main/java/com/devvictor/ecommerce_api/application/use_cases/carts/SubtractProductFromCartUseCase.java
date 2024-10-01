package com.devvictor.ecommerce_api.application.use_cases.carts;

import com.devvictor.ecommerce_api.application.dtos.input.SubtractProductFromCartInputDTO;
import com.devvictor.ecommerce_api.application.exceptions.NotFoundException;
import com.devvictor.ecommerce_api.application.services.CartService;
import com.devvictor.ecommerce_api.domain.entities.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SubtractProductFromCartUseCase {
    private final CartService cartService;

    public void execute(SubtractProductFromCartInputDTO dto) {
        Optional<Cart> cart = cartService.findByUserId(dto.userId());

        if (cart.isEmpty()) {
            throw new NotFoundException("Cart not found");
        }

        Cart existentCart = cart.get();

        existentCart.subtractProduct(dto.productId(), dto.productQnt());

        if (existentCart.getProductsQuantity() == 0) {
            cartService.delete(existentCart);
            return;
        }

        cartService.update(existentCart);
    }
}

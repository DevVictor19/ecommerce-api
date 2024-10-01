package com.devvictor.ecommerce_api.application.use_cases;

import com.devvictor.ecommerce_api.application.dtos.input.ClearCartInputDTO;
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

    public void execute(ClearCartInputDTO dto) {
        Optional<Cart> cart = cartService.findByUserId(dto.userId());

        if (cart.isEmpty()) {
            throw new NotFoundException("Cart not found");
        }

        Cart existentCart = cart.get();

        cartService.delete(existentCart);
    }
}

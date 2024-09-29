package com.devvictor.ecommerce_api.application.use_cases;

import com.devvictor.ecommerce_api.application.dtos.input.FindCartByUserInputDTO;
import com.devvictor.ecommerce_api.application.dtos.output.CartOutputDTO;
import com.devvictor.ecommerce_api.application.dtos.output.CartProductOutputDTO;
import com.devvictor.ecommerce_api.application.exceptions.NotFoundException;
import com.devvictor.ecommerce_api.application.services.CartService;
import com.devvictor.ecommerce_api.domain.entities.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindCartByUserUseCase {
    private final CartService cartService;

    public CartOutputDTO execute(FindCartByUserInputDTO dto) {
        Optional<Cart> cart = cartService.findByUserId(dto.userId());

        if (cart.isEmpty()) {
            throw new NotFoundException("Cart not found");
        }

       return toOutputDTO(cart.get());
    }

    private CartOutputDTO toOutputDTO(Cart input) {
        return new CartOutputDTO(
            input.getId(),
            input.getProducts().stream()
                .map(p -> new CartProductOutputDTO(
                        p.getId(),
                        p.getPrice(),
                        p.getDescription(),
                        p.getPhotoUrl(),
                        p.getInCartQuantity()
                ))
                .toList(),
            input.getProductsQuantity(),
            input.getTotalPrice()
        );
    }
}

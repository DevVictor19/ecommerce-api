package com.devvictor.ecommerce_api.application.use_cases.carts;

import com.devvictor.ecommerce_api.application.dtos.input.FindCartByUserInputDTO;
import com.devvictor.ecommerce_api.application.dtos.output.CartOutputDTO;
import com.devvictor.ecommerce_api.application.exceptions.NotFoundException;
import com.devvictor.ecommerce_api.application.mappers.CartEntityMapper;
import com.devvictor.ecommerce_api.application.services.CartService;
import com.devvictor.ecommerce_api.domain.entities.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindCartByUserUseCase {
    private final CartService cartService;
    private final CartEntityMapper cartEntityMapper;

    public CartOutputDTO execute(FindCartByUserInputDTO dto) {
        Optional<Cart> cart = cartService.findByUserId(dto.userId());

        if (cart.isEmpty()) {
            throw new NotFoundException("Cart not found");
        }

        return cartEntityMapper.toDto(cart.get());
    }
}

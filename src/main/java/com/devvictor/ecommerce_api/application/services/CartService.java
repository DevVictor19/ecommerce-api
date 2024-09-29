package com.devvictor.ecommerce_api.application.services;

import com.devvictor.ecommerce_api.domain.entities.Cart;
import com.devvictor.ecommerce_api.domain.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public void create(Cart cart) {
        cartRepository.insert(cart);
    }

    public void update(Cart cart) {
        cartRepository.save(cart);
    }

    public Optional<Cart> findByUserId(String userId) {
        return cartRepository.findByUserId(userId);
    }
}

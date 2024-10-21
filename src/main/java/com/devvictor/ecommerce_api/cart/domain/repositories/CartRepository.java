package com.devvictor.ecommerce_api.cart.domain.repositories;

import com.devvictor.ecommerce_api.cart.domain.entities.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart, String> {
    Optional<Cart> findByUserId(String userId);
}

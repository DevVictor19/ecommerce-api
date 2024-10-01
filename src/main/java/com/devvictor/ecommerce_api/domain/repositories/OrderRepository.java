package com.devvictor.ecommerce_api.domain.repositories;

import com.devvictor.ecommerce_api.domain.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {
    Optional<Order> findByIdAndUserId(String id, String userId);
}

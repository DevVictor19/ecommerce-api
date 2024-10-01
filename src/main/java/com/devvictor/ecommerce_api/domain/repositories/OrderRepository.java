package com.devvictor.ecommerce_api.domain.repositories;

import com.devvictor.ecommerce_api.domain.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String>{
}

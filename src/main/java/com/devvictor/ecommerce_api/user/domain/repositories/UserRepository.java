package com.devvictor.ecommerce_api.user.domain.repositories;

import com.devvictor.ecommerce_api.user.domain.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
   Optional<User> findByEmail(String email);
}

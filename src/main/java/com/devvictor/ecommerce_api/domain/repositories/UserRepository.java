package com.devvictor.ecommerce_api.domain.repositories;

import com.devvictor.ecommerce_api.domain.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}

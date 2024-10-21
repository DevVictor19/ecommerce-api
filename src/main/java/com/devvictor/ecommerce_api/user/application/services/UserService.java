package com.devvictor.ecommerce_api.user.application.services;

import com.devvictor.ecommerce_api.user.domain.entities.User;
import com.devvictor.ecommerce_api.user.domain.factories.UserFactory;
import com.devvictor.ecommerce_api.user.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User create(String username, String email, String password) {
        User user = UserFactory.create(username, email, password);

        return userRepository.insert(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

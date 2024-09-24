package com.devvictor.ecommerce_api.infra.providers;

import com.devvictor.ecommerce_api.application.providers.HashProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class HashProviderImpl implements HashProvider {
    private final PasswordEncoder passwordEncoder;

    public HashProviderImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String hash(String value) {
        return passwordEncoder.encode(value);
    }

    @Override
    public boolean compare(String value, String encoded) {
        return passwordEncoder.matches(value, encoded);
    }
}

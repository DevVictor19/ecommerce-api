package com.devvictor.ecommerce_api.infra.providers;

import com.devvictor.ecommerce_api.application.providers.HashProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HashProviderImpl implements HashProvider {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String hash(String value) {
        return passwordEncoder.encode(value);
    }

    @Override
    public boolean compare(String value, String encoded) {
        return passwordEncoder.matches(value, encoded);
    }
}

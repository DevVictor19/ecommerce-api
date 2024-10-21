package com.devvictor.ecommerce_api.user.application.providers;

public interface HashProvider {
    String hash(String value);
    boolean compare(String value, String encoded);
}

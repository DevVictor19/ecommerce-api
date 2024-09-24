package com.devvictor.ecommerce_api.application.providers;

public interface HashProvider {
    String hash(String value);
    boolean compare(String value, String encoded);
}

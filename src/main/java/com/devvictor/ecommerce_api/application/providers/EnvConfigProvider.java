package com.devvictor.ecommerce_api.application.providers;

public interface EnvConfigProvider {
    String getDatabaseHost();
    String getDatabaseName();
    String getDatabaseUser();
    String getDatabasePassword();
    Integer getDatabasePort();
    String getServerUrl();
    String getServerSecret();
}

package com.devvictor.ecommerce_api.shared.application.providers;

public interface EnvConfigProvider {
    String getDatabaseHost();
    String getDatabaseName();
    String getDatabaseUser();
    String getDatabasePassword();
    Integer getDatabasePort();
    String getServerUrl();
    String getServerSecret();
    String getPaymentGatewayUrl();
    String getPaymentGatewayKey();
}

package com.devvictor.ecommerce_api.infra.providers;

import com.devvictor.ecommerce_api.application.providers.EnvConfigProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvConfigProviderImpl implements EnvConfigProvider {
    @Value("${database.host}")
    private String databaseHost;

    @Value("${database.name}")
    private String databaseName;

    @Value("${database.user}")
    private String databaseUser;

    @Value("${database.pwd}")
    private String databasePassword;

    @Value("${database.port}")
    private Integer databasePort;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${server.secret}")
    private String serverSecret;

    @Override
    public String getDatabaseHost() {
        if (databaseHost == null) {
            throw new RuntimeException("Database env variable host is null");
        }
        return databaseHost;
    }

    @Override
    public String getDatabaseName() {
        if (databaseName == null) {
            throw new RuntimeException("Database env variable name is null");
        }
        return databaseName;
    }

    @Override
    public String getDatabaseUser() {
        if (databaseUser == null) {
            throw new RuntimeException("Database env variable user is null");
        }
        return databaseUser;
    }

    @Override
    public String getDatabasePassword() {
        if (databasePassword == null) {
            throw new RuntimeException("Database env variable pwd is null");
        }
        return databasePassword;
    }

    @Override
    public Integer getDatabasePort() {
        if (databasePort == null) {
            throw new RuntimeException("Database env variable port is null");
        }
        return databasePort;
    }

    @Override
    public String getServerUrl() {
        if (serverUrl == null) {
            throw new RuntimeException("Server env variable url is null");
        }
        return serverUrl;
    }

    @Override
    public String getServerSecret() {
        if (serverSecret == null) {
            throw new RuntimeException("Server env variable secret is null");
        }
        return serverSecret;
    }
}

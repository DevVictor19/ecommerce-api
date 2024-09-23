package com.devvictor.ecommerce_api.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvConfig {
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

    public String getDatabaseHost() {
        if (databaseHost == null) {
            throw new RuntimeException("Database env variable host is null");
        }
        return databaseHost;
    }

    public String getDatabaseName() {
        if (databaseName == null) {
            throw new RuntimeException("Database env variable name is null");
        }
        return databaseName;
    }

    public String getDatabaseUser() {
        if (databaseUser == null) {
            throw new RuntimeException("Database env variable user is null");
        }
        return databaseUser;
    }

    public String getDatabasePassword() {
        if (databasePassword == null) {
            throw new RuntimeException("Database env variable pwd is null");
        }
        return databasePassword;
    }

    public Integer getDatabasePort() {
        if (databasePort == null) {
            throw new RuntimeException("Database env variable port is null");
        }
        return databasePort;
    }
}


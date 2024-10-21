package com.devvictor.ecommerce_api.shared.infra.connections;

import com.devvictor.ecommerce_api.shared.application.providers.EnvConfigProvider;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
@RequiredArgsConstructor
public class MongoAppConfig {
    private final EnvConfigProvider envConfigProvider;

    private String getDatabaseUri() {
        return "mongodb://"
                +envConfigProvider.getDatabaseUser()+":"
                +envConfigProvider.getDatabasePassword()+"@"
                +envConfigProvider.getDatabaseHost()+":"
                +envConfigProvider.getDatabasePort();
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(
                MongoClients.create(getDatabaseUri()),
                envConfigProvider.getDatabaseName()
        );
    }
}

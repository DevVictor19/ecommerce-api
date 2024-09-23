package com.devvictor.ecommerce_api.infra.config;

import com.mongodb.MongoClientSettings.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import static java.util.Collections.singletonList;

@Configuration
public class MongoAppConfig extends AbstractMongoClientConfiguration {
    private final EnvConfig env;

    public MongoAppConfig(EnvConfig env) {
        this.env = env;
    }

    @Override
    protected String getDatabaseName() {
        return env.getDatabaseName();
    }

    @Override
    protected void configureClientSettings(Builder builder) {

        builder.credential(MongoCredential
                        .createCredential(env.getDatabaseUser(), env.getDatabaseName(), env.getDatabasePassword()
                        .toCharArray()))
                .applyToClusterSettings(settings  -> {
                    settings.hosts(singletonList(
                            new ServerAddress(env.getDatabaseHost(), env.getDatabasePort()))
                    );
                });

    }
}

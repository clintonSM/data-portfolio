package com.bim.pruebatecnica.config.mongodb;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MongoConfigurations {
    @Autowired
    private CustomMongoProperties customMongoProperties;

    @Primary
    @Bean
    public MongoClient reactiveMongoClientLoc() {
        return MongoClients.create(createMongoSettings(customMongoProperties.getLoc()));
    }

    @Primary
    @Bean("mongoTemplate")
    public ReactiveMongoTemplate reactiveMongoTemplateLoc() {
        return new ReactiveMongoTemplate(reactiveMongoClientLoc(), customMongoProperties.getLoc().getDatabase());
    }

    private MongoClientSettings createMongoSettings(ConfigProperties mongoProperties) {
        
        ConnectionString connectionString = new ConnectionString(mongoProperties.getUri());
        // MongoCredential credential = MongoCredential.createCredential(mongoProperties.getUsername(),
        //         mongoProperties.getDatabase(), mongoProperties.getPassword());

        return MongoClientSettings.builder()
                .readConcern(ReadConcern.DEFAULT)
                .writeConcern(WriteConcern.MAJORITY)
                .readPreference(ReadPreference.primary())
                //.credential(credential)
                .applyToSslSettings(builder -> {
                    builder.enabled(mongoProperties.isSsl());
                    builder.invalidHostNameAllowed(true);
                })
                .applyConnectionString(connectionString)
                .applyToConnectionPoolSettings(builder -> {
                    builder.maxConnectionIdleTime(mongoProperties.getIdleTimeout(), TimeUnit.MILLISECONDS);
                    builder.maxConnectionLifeTime(mongoProperties.getMaxLifeTiem(), TimeUnit.MILLISECONDS);
                })
                .applyToSocketSettings(builder -> builder.connectTimeout(mongoProperties.getConnectionTimeout(),
                        TimeUnit.MILLISECONDS))
                .build();
    }

}

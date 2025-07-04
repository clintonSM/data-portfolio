package com.bim.pruebatecnica.config.mongodb;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConfigProperties extends MongoProperties {
    private boolean ssl;
    private int connectionTimeout;
    private int idleTimeout;
    private int maxLifeTiem;
}

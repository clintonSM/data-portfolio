package com.bim.pruebatecnica.repository.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.bim.pruebatecnica.repository", reactiveMongoTemplateRef = "mongoTemplate")
public class MongoConfig {

}

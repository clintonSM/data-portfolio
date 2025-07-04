package com.bim.pruebatecnica.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bim.pruebatecnica.model.entity.UserEntity;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<UserEntity, String> {
    Mono<UserEntity> findByEmail(String email);
}

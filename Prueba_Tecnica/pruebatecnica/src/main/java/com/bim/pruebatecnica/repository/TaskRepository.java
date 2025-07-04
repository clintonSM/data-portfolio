package com.bim.pruebatecnica.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bim.pruebatecnica.model.entity.TaskEntity;

import reactor.core.publisher.Mono;

public interface TaskRepository extends ReactiveMongoRepository<TaskEntity, String> {
    Mono<TaskEntity> findById(String id);
}

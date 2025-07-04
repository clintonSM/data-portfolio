package com.bim.pruebatecnica.bussiness;

import com.bim.pruebatecnica.model.entity.TaskEntity;
import com.bim.pruebatecnica.model.task.TaskRequest;
import com.bim.pruebatecnica.model.task.taskResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskService {
    Flux<TaskEntity> retrieveTasks();

    Mono<taskResponse> newTask(TaskRequest request);

    Mono<taskResponse> updateTask(TaskRequest request, String id);

    Mono<taskResponse> deleteTask(String id);
}

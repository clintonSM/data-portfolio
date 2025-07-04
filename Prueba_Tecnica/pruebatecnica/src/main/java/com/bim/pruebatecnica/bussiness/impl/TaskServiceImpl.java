package com.bim.pruebatecnica.bussiness.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bim.pruebatecnica.bussiness.TaskService;
import com.bim.pruebatecnica.model.entity.TaskEntity;
import com.bim.pruebatecnica.model.task.TaskRequest;
import com.bim.pruebatecnica.model.task.taskResponse;
import com.bim.pruebatecnica.repository.TaskRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository repository;

    @Override
    public Flux<TaskEntity> retrieveTasks() {
        return repository.findAll();
    }

    @Override
    public Mono<taskResponse> newTask(TaskRequest request) {
        TaskEntity tareas = new TaskEntity();
        tareas.setId(request.getId());
        tareas.setTitle(request.getTitle());
        tareas.setDetail(request.getDetail());
        return repository.save(tareas).flatMap(rp -> {
            return Mono.just(taskResponse.builder()
                    .id(request.getId())
                    .status(": NUEVO")
                    .build());
        }).onErrorMap(e -> {
            throw new RuntimeException(e.getMessage());
        });
    }

    @Override
    public Mono<taskResponse> updateTask(TaskRequest request, String id) {
        return repository.findById(id).flatMap(rep -> {
            rep.setDetail(request.getDetail());
            return repository.save(rep);
        })
                .flatMap(up -> {
                    return Mono.just(taskResponse.builder()
                            .id(up.getId())
                            .status(": UPDATE")
                            .build());
                });
    }

    @Override
    public Mono<taskResponse> deleteTask(String id) {
        return repository.findById(id).flatMap(rep -> repository.delete(rep)
                .then(Mono.just(taskResponse.builder()
                        .id(id)
                        .status("ELIMINADO")
                        .build())));
    }

}

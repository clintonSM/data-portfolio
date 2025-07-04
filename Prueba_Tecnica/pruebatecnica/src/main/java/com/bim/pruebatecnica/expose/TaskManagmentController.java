package com.bim.pruebatecnica.expose;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bim.pruebatecnica.bussiness.TaskService;
import com.bim.pruebatecnica.model.entity.TaskEntity;
import com.bim.pruebatecnica.model.task.TaskRequest;
import com.bim.pruebatecnica.model.task.taskResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class TaskManagmentController {
    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    public Flux<TaskEntity> getTasks() {
        log.info("refreshtokenonpremise [New trx (" + UUID.randomUUID().toString() + ")]");
        log.info("Operacion: Retrieve Tasks");

        return taskService.retrieveTasks();
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<taskResponse> newTask(@RequestBody TaskRequest request) {
        log.info("refreshtokenonpremise [New trx (" + UUID.randomUUID().toString() + ")]");
        log.info("Operacion: New task");

        return taskService.newTask(request);
    }

    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<taskResponse> updateTask(@RequestBody TaskRequest request, @PathVariable String id) {
        log.info("refreshtokenonpremise [New trx (" + UUID.randomUUID().toString() + ")]");
        log.info("Operacion: Update task");

        return taskService.updateTask(request,id);
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<taskResponse> removeTask(@PathVariable String id) {
        log.info("refreshtokenonpremise [New trx (" + UUID.randomUUID().toString() + ")]");
        log.info("Operacion: Delete Task");

        return taskService.deleteTask(id);
    }
}

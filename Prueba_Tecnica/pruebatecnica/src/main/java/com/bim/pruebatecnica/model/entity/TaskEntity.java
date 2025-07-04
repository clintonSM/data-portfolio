package com.bim.pruebatecnica.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tareas")
public class TaskEntity {
    @Id
    @Field(value = "id")
    private String id;

    @Field(value = "title")
    private String title;

    @Field(value = "detail")
    private String detail;
}

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
@Document(collection = "usuarios")
public class UserEntity {
    @Id
    @Field(value = "id")
    private String id;

    @Field(value = "email")
    private String email;

    @Field(value = "password")
    private String password;
}

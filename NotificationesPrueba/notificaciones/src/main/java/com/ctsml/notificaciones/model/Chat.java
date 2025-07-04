package com.ctsml.notificaciones.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Chat {
    Long id;
    String first_name;
    String type;
}

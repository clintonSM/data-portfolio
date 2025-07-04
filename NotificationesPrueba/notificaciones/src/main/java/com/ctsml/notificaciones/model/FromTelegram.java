package com.ctsml.notificaciones.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FromTelegram {
    Long id;
    Boolean is_bot;
    String first_name;
    String language_code;
}

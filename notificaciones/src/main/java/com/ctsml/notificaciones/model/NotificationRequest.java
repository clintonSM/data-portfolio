package com.ctsml.notificaciones.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class NotificationRequest {
    int update_id;
    MessageTelegram message;
}

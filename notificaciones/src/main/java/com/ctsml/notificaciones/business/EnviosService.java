package com.ctsml.notificaciones.business;

import com.ctsml.notificaciones.model.MessageResponse;
import com.ctsml.notificaciones.model.NotificationRequest;

import reactor.core.publisher.Mono;

public interface EnviosService {
    
    Mono<MessageResponse> envios(NotificationRequest request);
}

package com.ctsml.notificaciones.business;

import org.telegram.telegrambots.meta.api.objects.Update;

import reactor.core.publisher.Mono;

public interface EnviosService {
    
    Mono<Void> envios(Update request);
    Mono<Void> handleCallback(Update request);
}

package com.ctsml.notificaciones.business;

import org.telegram.telegrambots.meta.api.objects.Update;

import reactor.core.publisher.Mono;

/**
     * Servicio para manejar los mensajes enviados a los usuarios.
     * Encargado de enviar notificaciones o mensajes personalizados.
     */
public interface MessageService {

    Mono<Void> envios(Update update);
    
}

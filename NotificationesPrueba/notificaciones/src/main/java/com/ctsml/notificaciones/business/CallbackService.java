package com.ctsml.notificaciones.business;

import org.telegram.telegrambots.meta.api.objects.Update;

import reactor.core.publisher.Mono;

/**
 * Servicio para manejar las respuestas o acciones posteriores a una interacción
 * de usuario.
 * Encargado de procesar los callbacks generados por los botones inline.
 */
public interface CallbackService {

    Mono<Void> handleCallback(Update update);

}

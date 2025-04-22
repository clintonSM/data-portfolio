package com.ctsml.notificaciones.business;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import reactor.core.publisher.Mono;

public interface NotificationService {

    Mono<Void> sendMessage(SendMessage request);

}

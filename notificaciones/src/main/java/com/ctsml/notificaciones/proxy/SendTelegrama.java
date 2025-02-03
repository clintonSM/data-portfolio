package com.ctsml.notificaciones.proxy;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import reactor.core.publisher.Mono;

public interface SendTelegrama {

    Mono<Void> sendMessage(SendMessage message);
    
}

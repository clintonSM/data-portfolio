package com.ctsml.notificaciones.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.ctsml.notificaciones.business.NotificationService;
import com.ctsml.notificaciones.proxy.SendTelegrama;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    SendTelegrama sendTelegrama;

    @Override
    public Mono<Void> sendMessage(SendMessage request) {
        log.info("Envio de notificacion");
        return sendTelegrama.sendMessage(request);
    }

}

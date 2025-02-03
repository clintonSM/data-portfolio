package com.ctsml.notificaciones.proxy.impl;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.ctsml.notificaciones.proxy.SendTelegrama;

import reactor.core.publisher.Mono;

@Service
public class sendTelegramImpl implements SendTelegrama {

    @Autowired
    @Qualifier("telgramNotify")
    private WebClient webClient;

    @Override
    public Mono<Void> sendMessage(SendMessage message) {
        return webClient.post().header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(message)
                .retrieve()
                .bodyToMono(Void.class);

    }

}

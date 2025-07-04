package com.ctsml.notificaciones.proxy.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.ctsml.notificaciones.proxy.SendTelegrama;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class sendTelegramImpl implements SendTelegrama {

    @Autowired
    @Qualifier("telgramNotify")
    private WebClient webClient;

    @Override
    public Mono<Void> sendMessage(SendMessage message) {
        log.info(message.toString());
        return webClient.post().header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(message)
                .retrieve()
                .bodyToMono(Void.class);

    }

}

package com.ctsml.notificaciones.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ctsml.notificaciones.business.EnviosService;
import com.ctsml.notificaciones.model.MessageResponse;
import com.ctsml.notificaciones.model.NotificationRequest;
import com.ctsml.notificaciones.model.TelegramRequest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class EnviosServiceImpl implements EnviosService {

    @Autowired
    @Qualifier("telgramNotify")
    private WebClient webClient;

    @Override
    public Mono<MessageResponse> envios(NotificationRequest request) {

        TelegramRequest telgram = TelegramRequest.builder()
                .chat_id(request.getMessage().getChat().getId().toString())
                .text("Hola tu mensaje ha sido recibido").build();
        return webClient.post().header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(telgram).retrieve()
                .toEntity(Object.class)
                .onErrorMap(error -> {
                    log.info("ERROR :" + error.getMessage().toString());
                    throw new RuntimeException("Error al enviar a Telegram");
                }).map(result -> {
                    return MessageResponse.builder().status("Enviado").build();
                });

    }

}

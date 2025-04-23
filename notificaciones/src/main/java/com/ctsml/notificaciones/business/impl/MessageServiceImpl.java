package com.ctsml.notificaciones.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import com.ctsml.notificaciones.business.MessageService;
import com.ctsml.notificaciones.proxy.RetrieveClima;
import com.ctsml.notificaciones.proxy.SendTelegrama;
import com.ctsml.notificaciones.util.Utility;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    SendTelegrama sendTelegrama;

    @Autowired
    RetrieveClima retrieveClima;

    @Override
    public Mono<Void> envios(Update update) {
        log.info("INICIO ENVIOS");
        if (update.getMessage().hasLocation()) {
            log.info("ENVIO UBICACION");
            InlineKeyboardMarkup markup = Utility.finalizarDiag();
            return retrieveClima.getClima("Lima").flatMap(resp -> {
                SendMessage message = new SendMessage();
                message.setChatId(update.getMessage().getChat().getId().toString());
                message.setText(resp);
                message.setReplyMarkup(markup);
                return sendTelegrama.sendMessage(message);
            });
        } else {
            log.info("Respuesta a handleCallback false");
            String text = ("Hola " + update.getMessage().getFrom().getFirstName() + " ¿Qué desea revisar hoy?");
            log.info(text);
            InlineKeyboardMarkup inlineKeyboardMarkup = Utility.crearMenuOpciones();
            return sendTelegrama
                    .sendMessage(SendMessage.builder()
                            .chatId(update.getMessage().getChat().getId().toString())
                            .text(text)
                            .replyMarkup(inlineKeyboardMarkup)
                            .build());
        }

    }

}

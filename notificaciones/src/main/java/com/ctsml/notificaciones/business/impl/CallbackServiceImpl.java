package com.ctsml.notificaciones.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.ctsml.notificaciones.business.CallbackService;
import com.ctsml.notificaciones.proxy.SendTelegrama;
import com.ctsml.notificaciones.util.Utility;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CallbackServiceImpl implements CallbackService {

    @Autowired
    SendTelegrama sendTelegrama;

    @Override
    public Mono<Void> handleCallback(Update update) {
        log.info("Ineteracciones con el bot");
        String callbackData = update.getCallbackQuery().getData();
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        InlineKeyboardMarkup markup = finalizarDiag();
        SendMessage message = new SendMessage();
        String responseMessage;
        switch (callbackData) {
            case "clima":
                responseMessage = "Aquí tienes la información del clima: ☀️ 25°C" + "\n\n¿Desea revisar algo mas?";
                message.setChatId(chatId);
                message.setText(responseMessage);
                message.setReplyMarkup(markup);
                return sendTelegrama.sendMessage(message);

            case "trafico":
                responseMessage = "El tráfico está ligero en tu zona 🚗" + "\n\n¿Desea revisar algo mas?";
                message.setChatId(chatId);
                message.setText(responseMessage);
                message.setReplyMarkup(markup);
                return sendTelegrama.sendMessage(message);

            case "eventos":
                responseMessage = "Eventos locales próximos: Concierto el sábado 🎶"
                        + "\n\n¿Desea revisar algo mas?";
                message.setChatId(chatId);
                message.setText(responseMessage);
                message.setReplyMarkup(markup);
                return sendTelegrama.sendMessage(message);
            case "recordatorios":
                responseMessage = "No tienes recordatorios pendientes 📅" + "\n\n¿Desea revisar algo mas?";
                message.setChatId(chatId);
                message.setText(responseMessage);
                message.setReplyMarkup(markup);
                return sendTelegrama.sendMessage(message);
            case "finalizar":
                responseMessage = "Gracias por usar el servicio. ¡Hasta pronto!";
                message.setChatId(chatId);
                message.setText(responseMessage);
                return sendTelegrama.sendMessage(message);
            case "continuar":
                responseMessage = "¿Qué deseas revisar ahora?";
                InlineKeyboardMarkup markupCont = Utility.crearMenuOpciones();
                message.setChatId(chatId);
                message.setText(responseMessage);
                message.setReplyMarkup(markupCont);
                return sendTelegrama.sendMessage(message);
            default:
                responseMessage = "Opción no válida.";
                message.setChatId(chatId);
                message.setText(responseMessage);
                return sendTelegrama.sendMessage(message);
        }
    }

    private InlineKeyboardMarkup finalizarDiag() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton continueButton = new InlineKeyboardButton("Nueva consulta");
        continueButton.setCallbackData("continuar");

        InlineKeyboardButton endButton = new InlineKeyboardButton("Finalizar");
        endButton.setCallbackData("finalizar");

        row.add(continueButton);
        row.add(endButton);
        rowsInline.add(row);
        markup.setKeyboard(rowsInline);
        return markup;
    }

}

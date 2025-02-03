package com.ctsml.notificaciones.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.ctsml.notificaciones.business.EnviosService;
import com.ctsml.notificaciones.proxy.SendTelegrama;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class EnviosServiceImpl implements EnviosService {

    @Autowired
    @Qualifier("telgramNotify")
    private WebClient webClient;

    @Autowired
    SendTelegrama sendTelegrama;

    @Override
    public Mono<Void> envios(Update request) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        // Creación de botones
        InlineKeyboardButton buttonA = new InlineKeyboardButton("A. Clima");
        buttonA.setCallbackData("clima");

        InlineKeyboardButton buttonB = new InlineKeyboardButton("B. Tráfico");
        buttonB.setCallbackData("trafico");

        InlineKeyboardButton buttonC = new InlineKeyboardButton("C. Eventos Locales");
        buttonC.setCallbackData("eventos");

        InlineKeyboardButton buttonD = new InlineKeyboardButton("D. Recordatorios");
        buttonD.setCallbackData("recordatorios");

        // Añadir los botones a la fila
        row.add(buttonA);
        row.add(buttonB);
        row.add(buttonC);
        row.add(buttonD);

        // Añadir la fila a la lista de filas
        rowsInline.add(row);

        // Establecer el teclado
        inlineKeyboardMarkup.setKeyboard(rowsInline);

        SendMessage message = new SendMessage();
        message.setChatId(request.getMessage().getChat().getId().toString());
        message.setText("Hola " + request.getMessage().getFrom().getFirstName() + " ¿Qué desea revisar hoy?");
        message.setReplyMarkup(inlineKeyboardMarkup);

        return sendTelegrama.sendMessage(message);

    }

    @Override
    public Mono<Void> handleCallback(Update update) {
        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();

            String responseMessage;

            switch (callbackData) {
                case "clima":
                    responseMessage = "Aquí tienes la información del clima: ☀️ 25°C";
                    break;
                case "trafico":
                    responseMessage = "El tráfico está ligero en tu zona 🚗";
                    break;
                case "eventos":
                    responseMessage = "Eventos locales próximos: Concierto el sábado 🎶";
                    break;
                case "recordatorios":
                    responseMessage = "No tienes recordatorios pendientes 📅";
                    break;
                default:
                    responseMessage = "Opción no válida.";
            }

            InlineKeyboardMarkup markup = finalizarDiag();

            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(responseMessage + "\n\n¿Desea revisar algo mas?");
            message.setReplyMarkup(markup);
            return sendTelegrama.sendMessage(message);
        }
        return Mono.empty();
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

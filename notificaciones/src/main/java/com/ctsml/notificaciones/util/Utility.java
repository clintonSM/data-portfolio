package com.ctsml.notificaciones.util;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class Utility {

    public static InlineKeyboardMarkup crearMenuOpciones() {
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

        row.add(buttonA);
        row.add(buttonB);
        row.add(buttonC);
        row.add(buttonD);
        rowsInline.add(row);

        inlineKeyboardMarkup.setKeyboard(rowsInline);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup finalizarDiag() {
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

    public static ReplyKeyboardMarkup crearBotonUbicacion() {
        KeyboardButton locationButton = new KeyboardButton("📍 Enviar mi ubicación");
        locationButton.setRequestLocation(true);

        KeyboardRow row = new KeyboardRow();
        row.add(locationButton);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(List.of(row));
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);
        return keyboardMarkup;
    }

}

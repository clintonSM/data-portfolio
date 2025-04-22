package com.ctsml.notificaciones.util;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

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

}

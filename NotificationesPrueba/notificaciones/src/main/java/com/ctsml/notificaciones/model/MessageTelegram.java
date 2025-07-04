package com.ctsml.notificaciones.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MessageTelegram {
    int message_id;
    FromTelegram from;
    Chat chat;
    int date;
    String text;
}

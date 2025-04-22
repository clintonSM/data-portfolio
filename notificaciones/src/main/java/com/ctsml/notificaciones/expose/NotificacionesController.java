package com.ctsml.notificaciones.expose;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.ctsml.notificaciones.business.CallbackService;
import com.ctsml.notificaciones.business.MessageService;
import com.ctsml.notificaciones.business.NotificationService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Endpoint para manejar las notificaciones recibidas desde el cliente o
 * usuario.
 * 
 * @param update El objeto Update de Telegram que contiene la información de la
 *               interacción (mensaje o callback).
 * @return Mono<Void> Representa la respuesta no bloqueante del servicio.
 */
@RestController
@RequestMapping("/notifications")
@Slf4j
public class NotificacionesController {
    // Responsabilidad Única (Single Responsibility Principle)(MessageService y
    // CallbackService).

    @Autowired // inyecta automáticamente una implementación registrada
    private MessageService messageService; // 1) Manejar Mensajes

    @Autowired
    private CallbackService callbackService; // 2) Manejar callbacks (respuesta o acción posterior a una interacción del
                                             // usuario)c

    @Autowired
    private NotificationService notificationService;// 3) Envio de notificaciones

    @PostMapping("/send")
    public Mono<Void> handleCallbacks(@RequestBody Update update) {

        log.info("INICIO CALLBACKS");
        // Verifica si el update contiene una consulta de callback (interacción con
        // botón inline)
        if (update.hasCallbackQuery()) {
            log.info(update.toString());
            return callbackService.handleCallback(update);
        } else {
            log.info(update.toString());
            return messageService.envios(update);
        }
    }

    @PostMapping("/notify")
    public Mono<Void> sendNotification(@RequestBody SendMessage request) {

        log.info("INICIO NOTIFICACIONES");
        // Envio de notificaciones
        return notificationService.sendMessage(request);
    }

}

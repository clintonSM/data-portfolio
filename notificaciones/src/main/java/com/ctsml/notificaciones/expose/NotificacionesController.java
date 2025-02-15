package com.ctsml.notificaciones.expose;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.ctsml.notificaciones.business.EnviosService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/notifications")
@Slf4j
public class NotificacionesController {

    @Autowired
    EnviosService enviosService;

    @PostMapping("/send")
    public Mono<Void> postMethodName(@RequestBody Update update) {
        
        log.info("INICIO SEND");
        if (update.hasCallbackQuery()) {
            return enviosService.handleCallback(update);
        } else {
            return enviosService.envios(update);
        }
    }
 
    
}

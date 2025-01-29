package com.ctsml.notificaciones.expose;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctsml.notificaciones.business.EnviosService;
import com.ctsml.notificaciones.model.MessageResponse;
import com.ctsml.notificaciones.model.NotificationRequest;

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
    public Mono<MessageResponse> postMethodName(@RequestBody NotificationRequest request) {
        
        log.info("INICIO");
        return enviosService.envios(request);
    }
    
    
}

package com.ctsml.notificaciones.proxy;


import reactor.core.publisher.Mono;

public interface RetrieveClima {

    Mono<String> getClima(String city);
    
}

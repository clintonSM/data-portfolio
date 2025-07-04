package com.bim.pruebatecnica.expose;

import org.springframework.web.bind.annotation.RestController;

import com.bim.pruebatecnica.bussiness.AuthenticationService;
import com.bim.pruebatecnica.model.autenticacion.LoginResponse;
import com.bim.pruebatecnica.model.autenticacion.RegisterRequest;
import com.bim.pruebatecnica.model.autenticacion.RegisterResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired 
    private AuthenticationService authenticationService;

    /**
     * Endpoint para manejar el registro del cliente o
     * usuario.
     * 
     * @param RegisterRequest Objecto que contiene los datos del cliente o usuario
     * @return Mono<Void> Representa la respuesta no bloqueante del servicio.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<RegisterResponse> registerMethod(@RequestBody RegisterRequest request) {
        log.info("refreshtokenonpremise [New trx (" + UUID.randomUUID().toString() + ")]");
        log.info("Operacion: Registrar Usuario");

        return authenticationService.register(request);
    }

    /**
     * Endpoint para manejar el registro del cliente o
     * usuario.
     * 
     * @param RegisterRequest Objecto que contiene los datos del cliente o usuario
     * @return Mono<LoginResponse> Represnta el accessToken.
     */
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<LoginResponse> loginMethod(@RequestBody RegisterRequest request) {
        log.info("refreshtokenonpremise [New trx (" + UUID.randomUUID().toString() + ")]");
        log.info("Operacion: Login");

        return authenticationService.login(request);
    }

}

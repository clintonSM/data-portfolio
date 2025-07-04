package com.bim.pruebatecnica.bussiness;

import com.bim.pruebatecnica.model.autenticacion.LoginResponse;
import com.bim.pruebatecnica.model.autenticacion.RegisterRequest;
import com.bim.pruebatecnica.model.autenticacion.RegisterResponse;

import reactor.core.publisher.Mono;

public interface AuthenticationService {
    Mono<RegisterResponse> register(RegisterRequest request);
    Mono<LoginResponse> login(RegisterRequest request);
}

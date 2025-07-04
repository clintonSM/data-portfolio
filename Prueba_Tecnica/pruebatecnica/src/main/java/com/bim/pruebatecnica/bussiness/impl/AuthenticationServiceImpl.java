package com.bim.pruebatecnica.bussiness.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bim.pruebatecnica.bussiness.AuthenticationService;
import com.bim.pruebatecnica.config.security.JwtService;
import com.bim.pruebatecnica.model.autenticacion.LoginResponse;
import com.bim.pruebatecnica.model.autenticacion.RegisterRequest;
import com.bim.pruebatecnica.model.autenticacion.RegisterResponse;
import com.bim.pruebatecnica.model.entity.UserEntity;
import com.bim.pruebatecnica.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserRepository repository;

    @Autowired
    JwtService jwtService;

    @Override
    public Mono<RegisterResponse> register(RegisterRequest request) {

        UserEntity newuser = new UserEntity();
        newuser.setEmail(request.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        newuser.setPassword(encoder.encode(request.getPassword()));
        log.info("Usuario: " + newuser.toString());
        return repository.save(newuser).flatMap(rp -> {
            return Mono.just(RegisterResponse.builder()
                    .status(request.getEmail() + ": Registrado")
                    .build());
        }).onErrorMap(e -> {
            throw new RuntimeException(e.getMessage());
        });
    }

    @Override
    public Mono<LoginResponse> login(RegisterRequest request) {
        log.info("Usuario: " + request.getEmail());
        return repository.findByEmail(request.getEmail()).flatMap(resp -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            log.info("Usuario encontrado: " + resp.getEmail());
            if (resp != null && encoder.matches(request.getPassword(), resp.getPassword())) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("email", resp.getEmail());
                claims.put("roles", List.of("USER")); 
                String token = jwtService.generateToken(resp.getEmail(), claims);
                log.info("token:" + token);
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setAccesToken(token);
                return Mono.just(loginResponse);
            } else {
                throw new RuntimeException("Usuario No Registrado");
            }
        });
    }

}

package com.bim.pruebatecnica.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bim.pruebatecnica.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public Mono<UserDetails> findByUsername(String email) {
        log.info("Usuario: " + email);
        return repository.findByEmail(email)
                .map(user -> User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .roles("USER") // o user.getRoles().toArray()
                        .build());
    }

}

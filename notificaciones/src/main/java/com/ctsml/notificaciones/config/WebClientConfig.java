package com.ctsml.notificaciones.config;


import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import io.netty.resolver.DefaultAddressResolverGroup;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {
    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.chat.url}")
    private String urlTelegram;

    @Value("${weather.clima.url}")
    private String urlWeather;

    @Bean("telgramNotify")
    public WebClient telgramNotify() {
        String token = "bot" + botToken;
        String rest = urlTelegram + "/" + token + "/sendMessage";
        return WebClient.builder().baseUrl(rest)
                                .clientConnector(getClientConnector(10000, 10000, 10000))
                                .build();
    }

    @Bean("weatherStack")
    public WebClient weatherStack() {
        String rest = urlWeather;
        return WebClient.builder().baseUrl(rest)
                                .clientConnector(getClientConnector(10000, 10000, 10000))
                                .build();
    }

    public static ReactorClientHttpConnector getClientConnector(int connectionTimeout, int readTimeout, int writeTimeout){
        HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeout)
                                    .doOnConnected(conn-> conn.addHandlerLast(new ReadTimeoutHandler(readTimeout,TimeUnit.MILLISECONDS))
                                        .addHandlerLast(new WriteTimeoutHandler(writeTimeout,TimeUnit.MILLISECONDS)));

        return new ReactorClientHttpConnector(httpClient);
                                        
    }
}

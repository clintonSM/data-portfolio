package com.ctsml.notificaciones.proxy.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ctsml.notificaciones.proxy.RetrieveClima;
import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;

@Service
public class RetirieveClimaImpl implements RetrieveClima {

    @Autowired
    @Qualifier("weatherStack")
    private WebClient webClient;

    @Value("${weather.clima.token}")
    private String tokenWeather;

    @Override
    public Mono<String> getClima(String city) {

        String queryStr = "?access_key=" + tokenWeather + "&query=" + city;
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/weather")
                        .query(queryStr)
                        .build())
                .retrieve()
                .bodyToMono(JsonNode.class).map(resp -> {
                    return getData(resp);
                });

    }

    private String getData(JsonNode json) {
        String ciudad = json.path("location").path("name").asText();
        String pais = json.path("location").path("country").asText();
        String horaLocal = json.path("location").path("localtime").asText();

        String descripcion = json.path("current").path("weather_descriptions").get(0).asText();
        int temp = json.path("current").path("temperature").asInt();
        int feelsLike = json.path("current").path("feelslike").asInt();
        int humedad = json.path("current").path("humidity").asInt();
        int viento = json.path("current").path("wind_speed").asInt();
        String direccionViento = json.path("current").path("wind_dir").asText();
        String sunrise = json.path("current").path("astro").path("sunrise").asText();
        String sunset = json.path("current").path("astro").path("sunset").asText();

        String mensaje = String.format(
                "📍 %s, %s (%s)\n" +
                "🌤️ Clima: %s\n" +
                "🌡️ Temperatura: %d°C (se siente como %d°C)\n" +
                "💧 Humedad: %d%%\n" +
                "💨 Viento: %d km/h (%s)\n" +
                        "🌇 Amanecer: %s | Atardecer: %s",
                ciudad, pais, horaLocal,
                descripcion,
                temp, feelsLike,
                humedad,
                viento, direccionViento,
                sunrise, sunset);
        return mensaje;
    }
}
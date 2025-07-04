package com.bim.pruebatecnica.config.security;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService {

    // Puedes mover esta clave a una variable de entorno
    private static final String SECRET_KEY = "6F7A586E3272357538782F413F4428472B4B6250655368566D59713374367739";

    // Generar el token
    public String generateToken(String email, Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject(email) // el email
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extraer el username (subject) del token
    public String extractUsername(String token) {
        log.info("identificar user: " + token);
        String username = extractClaim(token, Claims::getSubject);
        log.info("Extracted subject (username): " + username);
        return username;
    }

    // Validar que el token sea válido y no esté expirado
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        log.info("Token username: " + username);
        log.info("UserDetails username: " + userDetails.getUsername());
        return username != null && username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // Verificar si el token ha expirado
    private boolean isTokenExpired(String token) {
        Date expiration = extractExpiration(token);
        log.info("Token expiration: " + expiration);
        return expiration.before(new Date());
    }

    // Extraer la fecha de expiración del token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extraer cualquier claim con función lambda
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        log.info("Extract Claims: " + claims);
        return claimsResolver.apply(claims);
    }

    // Parsear el token y extraer todos los claims
    private Claims extractAllClaims(String token) {
        try {
            return Jwts
                    .parserBuilder()
                    .setAllowedClockSkewSeconds(60)
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("Error al parsear el token JWT: {}", e.getMessage());
            throw new RuntimeException("Token inválido o mal firmado");
        }
    }

    // Obtener la clave de firma
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

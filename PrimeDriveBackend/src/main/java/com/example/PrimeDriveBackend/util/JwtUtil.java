package com.example.PrimeDriveBackend.util;

import java.sql.Date;
import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.config.JwtProperties;
import com.example.PrimeDriveBackend.model.PlattformNutzerkonto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;

@Component
public class JwtUtil {
    private final SecretKey secretKey;
    private final Integer EXPIRATION_TIME;
    private final JwtParser jwtParser;

    public JwtUtil(JwtProperties properties) {
        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(properties.getSecret()));
        this.EXPIRATION_TIME = properties.getExpirationTime();
        this.jwtParser = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build();
    }

    public String generateToken(Integer kontoId) {
        if (kontoId == null) {
            throw new IllegalArgumentException("KontoId cannot be null");
        }

        return Jwts.builder()
                .claim("id", kontoId)
                .setSubject(kontoId.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public Integer extractUserId(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return claims.get("id", Integer.class);
    }

    public boolean validateToken(String token, PlattformNutzerkonto user) {
        Integer extractedId = extractUserId(token);
        return extractedId.equals(user.getKontoId()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }

    public String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
package com.example.PrimeDriveBackend.util;

import java.sql.Date;
import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.config.JwtProperties;
import com.example.PrimeDriveBackend.model.PlattformNutzerkonto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
    private final String SECRET_KEY;
    private final Integer EXPIRATION_TIME;

    public JwtUtil(JwtProperties properties) {
        this.SECRET_KEY = properties.getSecret();
        this.EXPIRATION_TIME = properties.getExpirationTime();
    }

    public String generateToken(Integer kontoId) {
        return Jwts.builder()
                .claim("id", kontoId)
                .setSubject(kontoId.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + this.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public Integer extractUserId(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
                .getBody().get("id", Integer.class);
    }

    public boolean validateToken(String token, PlattformNutzerkonto id) {
        final Integer extractedId = extractUserId(token);
        return (extractedId.equals(id) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration()
                .before(new Date(System.currentTimeMillis()));
    }

    public String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}

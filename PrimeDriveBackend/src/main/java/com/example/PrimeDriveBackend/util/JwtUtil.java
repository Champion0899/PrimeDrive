package com.example.PrimeDriveBackend.util;

import java.sql.Date;
import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.config.JwtProperties;
import com.example.PrimeDriveBackend.model.Users;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;

/**
 * Utility class for handling JWT (JSON Web Token) creation, validation, and
 * parsing.
 *
 * Provides methods to generate secure tokens, validate them against users,
 * extract claims like user ID, and manage expiration logic.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
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

    /**
     * Generates a JWT token with the given user ID.
     *
     * @param id the user ID to include in the token
     * @return a signed JWT token as a String
     * @throws IllegalArgumentException if the ID is null
     */
    public String generateToken(String id) {
        if (id == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }

        return Jwts.builder()
                .claim("id", id)
                .setSubject(id.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Extracts the user ID from the given JWT token.
     *
     * @param token the JWT token
     * @return the user ID as a String
     */
    public String extractUserId(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return claims.get("id", String.class);
    }

    /**
     * Validates a JWT token by checking if it belongs to the given user and is not
     * expired.
     *
     * @param token the JWT token to validate
     * @param user  the user to compare the token's ID against
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String token, Users user) {
        String extractedId = extractUserId(token);
        return extractedId.equals(user.getId()) && !isTokenExpired(token);
    }

    /**
     * Checks if a JWT token has expired.
     *
     * @param token the JWT token to check
     * @return true if the token has expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }

    /**
     * Extracts the raw JWT token from an authorization header.
     *
     * @param authorizationHeader the HTTP Authorization header
     * @return the token without the "Bearer " prefix, or null if not present
     */
    public String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
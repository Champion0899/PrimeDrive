package com.example.PrimeDriveBackend.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/**
 * Service class for extracting client request information.
 *
 * Provides utility methods to retrieve metadata such as the client's IP address
 * from incoming HTTP requests.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Service
@RequiredArgsConstructor
public class RequestInfoService {
    /**
     * Retrieves the IP address of the client from the HTTP request.
     * Prioritizes the "X-Forwarded-For" header, falling back to the remote address
     * if absent.
     *
     * @param request the incoming HTTP request
     * @return the client's IP address as a string
     */
    public String getClientIp(HttpServletRequest request) {
        try {
            String clientIp = request.getHeader("X-Forwarded-For");
            if (clientIp == null || clientIp.isEmpty()) {
                clientIp = request.getRemoteAddr();
            }
            return clientIp;
        } catch (Exception e) {
            System.err.println("Failed to retrieve client IP: " + e.getMessage());
            return "UNKNOWN";
        }
    }
}

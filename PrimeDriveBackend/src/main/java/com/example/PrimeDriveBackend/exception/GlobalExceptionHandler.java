package com.example.PrimeDriveBackend.exception;

import com.example.PrimeDriveBackend.exception.UnauthorizedAccessException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

/**
 * Global exception handler for REST controllers.
 * Catches specific exceptions and returns structured error responses with HTTP
 * status codes.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0.0
 * Date: 2025-06-06
 */
@RestControllerAdvice(basePackages = "com.example.PrimeDriveBackend")
public class GlobalExceptionHandler {

    /**
     * Handles NoSuchElementException and returns a 404 Not Found response.
     *
     * @param ex The thrown NoSuchElementException
     * @return ResponseEntity with NOT_FOUND status and error message
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NoSuchElementException ex) {
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles IllegalArgumentException and returns a 400 Bad Request response.
     *
     * @param ex The thrown IllegalArgumentException
     * @return ResponseEntity with BAD_REQUEST status and error message
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException ex) {
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles EntityInUseException and returns a 409 Conflict response.
     *
     * @param ex The thrown EntityInUseException
     * @return ResponseEntity with CONFLICT status and error message
     */
    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<ErrorResponse> handleEntityInUse(EntityInUseException ex) {
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                LocalDateTime.now()), HttpStatus.CONFLICT);
    }

    /**
     * Handles UnauthorizedAccessException and returns a 401 Unauthorized response.
     *
     * @param ex The thrown UnauthorizedAccessException
     * @return ResponseEntity with UNAUTHORIZED status and error message
     */
    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedAccessException ex) {
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),
                LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles any other uncaught exceptions and returns a 500 Internal Server
     * Error.
     *
     * @param ex      The thrown generic Exception
     * @param request The HttpServletRequest object to get request details
     * @return ResponseEntity with INTERNAL_SERVER_ERROR status and generic error
     *         message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request) {
        String path = request.getRequestURI();

        if (path.startsWith("/swagger-ui/") || path.startsWith("/v3/api-docs/") || path.equals("/swagger-ui.html")) {
            // Do not handle Swagger-related exceptions here
            throw new RuntimeException(ex);
        }

        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ein unerwarteter Fehler ist aufgetreten.",
                LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Data structure representing a standardized error response.
     * Contains HTTP status code, message and timestamp.
     */
    public static class ErrorResponse {
        public int status;
        public String message;
        public LocalDateTime timestamp;

        public ErrorResponse(int status, String message, LocalDateTime timestamp) {
            this.status = status;
            this.message = message;
            this.timestamp = timestamp;
        }
    }
}
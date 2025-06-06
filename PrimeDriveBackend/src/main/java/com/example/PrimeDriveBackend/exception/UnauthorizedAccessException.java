package com.example.PrimeDriveBackend.exception;

/**
 * Exception thrown when a user attempts to access a resource without proper authorization.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0.0
 * Date: 2025-06-06
 */
public class UnauthorizedAccessException extends RuntimeException {
    /**
     * Constructs a new UnauthorizedAccessException with the specified detail message.
     *
     * @param message The detail message explaining the unauthorized access attempt.
     */
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}

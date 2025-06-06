package com.example.PrimeDriveBackend.exception;

/**
 * Exception thrown when an entity cannot be deleted or modified
 * because it is currently in use (e.g. referenced by another entity).
 *
 * Author: Fatlum Epiroti
 * Version: 1.0.0
 * Date: 2025-06-06
 */
public class EntityInUseException extends RuntimeException {
    /**
     * Constructs a new EntityInUseException with the specified detail message.
     *
     * @param message The detail message explaining the context of the exception.
     */
    public EntityInUseException(String message) {
        super(message);
    }
}

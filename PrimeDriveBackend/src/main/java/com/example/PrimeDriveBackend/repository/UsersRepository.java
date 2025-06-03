package com.example.PrimeDriveBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PrimeDriveBackend.model.Users;

/**
 * Repository interface for accessing and managing Users entity data.
 *
 * Extends JpaRepository to provide CRUD operations and adds custom query
 * methods
 * for user-specific lookups such as username existence and lookup by username
 * or ID.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    /**
     * Checks whether a user with the specified username exists.
     *
     * @param username the username to check
     * @return true if a user with the given username exists, false otherwise
     */
    boolean existsByUsername(String username);

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id the ID of the user
     * @return an Optional containing the user if found, or empty if not found
     */
    Optional<Users> findById(String id);

    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user
     * @return an Optional containing the user if found, or empty if not found
     */
    Optional<Users> findByUsername(String username);
}

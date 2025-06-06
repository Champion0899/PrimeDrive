package com.example.PrimeDriveBackend.service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.dto.UserDto;
import com.example.PrimeDriveBackend.dto.UserSafeDto;
import com.example.PrimeDriveBackend.mapper.UserMapper;
import com.example.PrimeDriveBackend.model.Users;
import com.example.PrimeDriveBackend.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing Users entity operations.
 *
 * Provides business logic for creating, reading, updating, and deleting users,
 * as well as converting between entity and DTO representations.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Checks if a user with the given username exists.
     *
     * @param username the username to check
     * @return true if a user with the given username exists, false otherwise
     */
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * Finds a user by username.
     *
     * @param username the username to search for
     * @return an Optional containing the user if found, or empty if not found
     */
    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Saves a Users entity to the repository.
     *
     * @param user the user entity to save
     * @return the saved user entity
     */
    public Users save(Users user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id the ID of the user
     * @return an Optional containing the user if found, or empty if not found
     */
    public Optional<Users> findById(String id) {
        return userRepository.findById(id);
    }

    /**
     * Retrieves a user by ID or throws an exception if not found.
     *
     * @param id the ID of the user
     * @return the user entity
     * @throws RuntimeException if no user is found
     */
    public Users getByIdEntity(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    /**
     * Retrieves all users as safe DTOs (excluding sensitive data).
     *
     * @return a list of UserSafeDto objects
     */
    public List<UserSafeDto> getAllUsersSafe() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toSafeDto)
                .toList();
    }

    /**
     * Retrieves a specific user as a safe DTO by ID.
     *
     * @param id the ID of the user
     * @return the UserSafeDto
     * @throws RuntimeException if the user is not found
     */
    public UserSafeDto getUserByIdSafe(String id) {
        return userRepository.findById(id)
                .map(userMapper::toSafeDto)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    /**
     * Creates a new user based on the provided DTO.
     *
     * @param userDto the DTO containing user data
     * @return the created UserDto
     */
    public UserDto createUser(UserDto userDto) {
        Users user = userMapper.toEntity(userDto);
        Users savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    /**
     * Updates an existing user by ID with data from the provided DTO.
     *
     * @param id      the ID of the user to update
     * @param userDto the updated user data
     * @return the updated UserDto
     * @throws RuntimeException if the user is not found
     */
    public UserDto updateUser(String id, UserDto userDto) {
        Users existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        Users updatedUser = userMapper.toEntity(userDto);
        updatedUser.setId(existingUser.getId());

        Users savedUser = userRepository.save(updatedUser);
        return userMapper.toDto(savedUser);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to delete
     * @throws RuntimeException if the user does not exist
     */
    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}

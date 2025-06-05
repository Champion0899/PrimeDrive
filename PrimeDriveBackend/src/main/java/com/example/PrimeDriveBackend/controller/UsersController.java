package com.example.PrimeDriveBackend.controller;

import com.example.PrimeDriveBackend.Dto.UserDto;
import com.example.PrimeDriveBackend.Dto.UserSafeDto;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.core.Authentication;

/**
 * Controller for managing users.
 * Provides endpoints for creating, updating, deleting, and retrieving user
 * information.
 * Access to post, put or delete is restricted based on user roles.
 * 
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-05-30
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Endpoints for managing users")
public class UsersController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Get all users", description = "Returns a list of all users. Access: ADMIN only.")
    public List<UserSafeDto> getAllUsers() {
        return userService.getAllUsersSafe();
    }

    /**
     * Endpoint to retrieve a specific user by their unique ID.
     *
     * This endpoint is accessible to all authenticated roles and returns
     * non-sensitive user data (UserSafeDto).
     *
     * @param id The unique identifier of the user to be retrieved
     * @return UserSafeDto containing public user information
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Returns a single user by their ID. Access: All authenticated roles.")
    public UserSafeDto getUserById(@PathVariable String id) {
        return userService.getUserByIdSafe(id);
    }

    /**
     * Endpoint to retrieve the currently authenticated user.
     *
     * This endpoint extracts the JWT token from the Spring Security context
     * and returns the corresponding user data (UserSafeDto).
     *
     * If no valid JWT is present, an IllegalStateException is thrown.
     *
     * @param authentication Spring Authentication object containing JWT
     * @return UserSafeDto representing the authenticated user
     */
    @GetMapping("/current")
    @Operation(summary = "Get current user", description = "Returns the currently authenticated user. Access: All authenticated roles.")
    public UserSafeDto getCurrentUser(Authentication authentication) {
        String userId = authenticationService.checkAuthentication(authentication);
        return userService.getUserByIdSafe(userId);
    }

    /**
     * Endpoint for administrators to create a new user.
     *
     * This method is restricted to users with the ADMIN role.
     * Accepts a UserDto object containing all necessary user details.
     *
     * @param userDto Data object containing user details to create
     * @return UserDto representing the newly created user
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Create a new user", description = "Creates a new user. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully."),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public UserDto createUser(@RequestBody UserDto userDto, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        return userService.createUser(userDto);
    }

    /**
     * Endpoint for administrators to update an existing user's data.
     *
     * The user is identified by their ID, and updated using the provided UserDto.
     * Only accessible to ADMIN users.
     *
     * @param id      Unique identifier of the user to be updated
     * @param userDto Object containing updated user data
     * @return UserDto with updated user information
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Update user", description = "Updates an existing user. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully."),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public UserDto updateUser(@PathVariable String id, @RequestBody UserDto userDto, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        return userService.updateUser(id, userDto);
    }

    /**
     * Endpoint to delete a user by their ID.
     *
     * Accessible only by administrators.
     * Permanently removes the user from the system.
     *
     * @param id Unique identifier of the user to be deleted
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Delete user", description = "Deletes a user by their ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully."),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public void deleteUser(@PathVariable String id, Authentication authentication) {
        authenticationService.checkAuthentication(authentication);
        userService.deleteUser(id);
    }
}

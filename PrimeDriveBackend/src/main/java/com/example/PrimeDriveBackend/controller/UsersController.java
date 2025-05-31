package com.example.PrimeDriveBackend.controller;

import com.example.PrimeDriveBackend.Dto.UserDto;
import com.example.PrimeDriveBackend.Dto.UserSafeDto;
import com.example.PrimeDriveBackend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import org.springframework.security.core.Authentication;

import org.springframework.security.oauth2.jwt.Jwt;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Endpoints for managing users")
public class UsersController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Get all users", description = "Returns a list of all users. Access: ADMIN only.")
    public List<UserSafeDto> getAllUsers() {
        return userService.getAllUsersSafe();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Returns a single user by their ID. Access: All authenticated roles.")
    public UserSafeDto getUserById(@PathVariable String id) {
        return userService.getUserByIdSafe(id);
    }

    @GetMapping("/current")
    @Operation(summary = "Get current user", description = "Returns the currently authenticated user. Access: All authenticated roles.")
    @SecurityRequirement(name = "bearer")
    public UserSafeDto getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()
                || !(authentication.getPrincipal() instanceof Jwt jwt)) {
            throw new IllegalStateException("JWT authentication expected but not found");
        }

        String userId = jwt.getSubject();
        return userService.getUserByIdSafe(userId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Create a new user", description = "Creates a new user. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully."),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Update user", description = "Updates an existing user. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully."),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public UserDto updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearer")
    @Operation(summary = "Delete user", description = "Deletes a user by their ID. Access: ADMIN only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully."),
            @ApiResponse(responseCode = "403", description = "Access denied – only ADMIN allowed")
    })
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}

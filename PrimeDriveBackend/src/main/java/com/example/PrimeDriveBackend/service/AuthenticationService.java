package com.example.PrimeDriveBackend.service;

import org.springframework.stereotype.Service;
import com.example.PrimeDriveBackend.model.Users;

import java.sql.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;

import lombok.RequiredArgsConstructor;

/**
 * Service class responsible for handling user registration and login
 * operations,
 * including password encoding and authentication validation.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-05-16
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new user by encoding the password and setting all relevant user
     * fields.
     *
     * @param username    The username of the new user
     * @param password    The raw password to be encoded
     * @param role        The assigned role (e.g. BUYER, SELLER)
     * @param email       The user's email address
     * @param birthdate   The user's date of birth
     * @param firstName   The user's first name
     * @param lastName    The user's last name
     * @param address     The user's address
     * @param zipCode     The user's ZIP/postal code
     * @param city        The user's city
     * @param country     The user's country
     * @param phoneNumber The user's contact phone number
     * @param lastLoginIp IP address of the last login (initially set during
     *                    registration)
     */
    public void registerUser(
            String username,
            String password,
            String role,
            String email,
            Date birthdate,
            String firstName,
            String lastName,
            String address,
            String zipCode,
            String city,
            String country,
            String phoneNumber,
            String lastLoginIp) {
        if (userService.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }

        Users user = new Users();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        user.setEMail(email);
        user.setBirthdate(birthdate);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setZipCode(zipCode);
        user.setCity(city);
        user.setCountry(country);
        user.setPhoneNumber(phoneNumber);
        user.setCreatedUser(username);
        user.setCreatedDate(new Date(System.currentTimeMillis()));
        user.setModifiedUser(username);
        user.setModifiedDate(new Date(System.currentTimeMillis()));
        user.setLastLoginDate(new Date(System.currentTimeMillis()));
        user.setLastLoginIp(lastLoginIp);

        userService.save(user);
    }

    /**
     * Compares a raw password with an encoded one.
     *
     * @param rawPassword     The plain text password input
     * @param encodedPassword The hashed password stored in the system
     * @return true if passwords match, otherwise throws an exception
     */
    public boolean comparePassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new IllegalArgumentException("Invalid password");
        }
        return true;
    }

    /**
     * Authenticates a user by checking if the username exists and the password is
     * correct.
     *
     * @param username The user's username
     * @param password The input password to verify
     * @return true if authentication is successful
     */
    public boolean login(String username, String password) {
        Users user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return comparePassword(password, user.getPassword());
    }

    public String checkAuthentication(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user found.");
        }

        Object principal = authentication.getPrincipal();
        String userId;
        if (principal instanceof Jwt jwt) {
            userId = jwt.getSubject();
        } else {
            userId = authentication.getName(); // fallback for session-based authentication
        }

        return userId;
    }
}

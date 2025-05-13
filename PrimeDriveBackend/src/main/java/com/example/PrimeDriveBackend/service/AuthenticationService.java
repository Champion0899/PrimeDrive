package com.example.PrimeDriveBackend.service;

import org.springframework.stereotype.Service;
import com.example.PrimeDriveBackend.model.Users;

import java.sql.Date;

import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(
            String username,
            String password,
            String role,
            String email,
            Date birthdate,
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

    public boolean comparePassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new IllegalArgumentException("Invalid password");
        }
        return true;
    }

    public boolean login(String username, String password) {
        Users user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return comparePassword(password, user.getPassword());
    }

}

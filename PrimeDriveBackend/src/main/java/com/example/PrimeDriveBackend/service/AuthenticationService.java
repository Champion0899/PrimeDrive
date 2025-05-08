package com.example.PrimeDriveBackend.service;

import org.springframework.stereotype.Service;
import com.example.PrimeDriveBackend.model.PlattformNutzerkonto;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PlattformNutzerkontoService plattformNutzerkontoService;
    private final PasswordEncoder passwordEncoder;

    public void registerPlattformNutzer(String username, String password, String role, String email) {
        if (plattformNutzerkontoService.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }

        PlattformNutzerkonto plattformNutzerkonto = new PlattformNutzerkonto();
        plattformNutzerkonto.setBenutzername(username);
        plattformNutzerkonto.setPasswort(passwordEncoder.encode(password));
        plattformNutzerkonto.setRolle(role);
        plattformNutzerkonto.setEMail(email);

        plattformNutzerkontoService.save(plattformNutzerkonto);
    }

    public boolean comparePassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new IllegalArgumentException("Invalid password");
        }
        return true;
    }

    public boolean login(String username, String password) {
        PlattformNutzerkonto plattformNutzerkonto = plattformNutzerkontoService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return comparePassword(password, plattformNutzerkonto.getPasswort());
    }

}

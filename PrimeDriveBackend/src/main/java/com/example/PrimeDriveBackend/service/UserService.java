package com.example.PrimeDriveBackend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.model.Users;
import com.example.PrimeDriveBackend.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository userRepository;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Users save(Users user) {
        return userRepository.save(user);
    }

    public Optional<Users> findById(Integer id) {
        return userRepository.findById(id);
    }
}

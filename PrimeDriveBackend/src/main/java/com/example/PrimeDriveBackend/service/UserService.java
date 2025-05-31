package com.example.PrimeDriveBackend.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.Dto.UserDto;
import com.example.PrimeDriveBackend.Dto.UserSafeDto;
import com.example.PrimeDriveBackend.Mapper.UserMapper;
import com.example.PrimeDriveBackend.model.Users;
import com.example.PrimeDriveBackend.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository userRepository;
    private final UserMapper userMapper;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Users save(Users user) {
        return userRepository.save(user);
    }

    public Optional<Users> findById(String id) {
        return userRepository.findById(id);
    }

    public Users getByIdEntity(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Users not found with id: " + id));
    }

    public List<UserSafeDto> getAllUsersSafe() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toSafeDto)
                .toList();
    }

    public UserSafeDto getUserByIdSafe(String id) {
        return userRepository.findById(id)
                .map(userMapper::toSafeDto)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public UserDto createUser(UserDto userDto) {
        Users user = userMapper.toEntity(userDto);
        Users savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserDto updateUser(String id, UserDto userDto) {
        Users existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        Users updatedUser = userMapper.toEntity(userDto);
        updatedUser.setId(existingUser.getId());

        Users savedUser = userRepository.save(updatedUser);
        return userMapper.toDto(savedUser);
    }

    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}

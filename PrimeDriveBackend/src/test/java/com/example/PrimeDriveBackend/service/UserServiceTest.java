package com.example.PrimeDriveBackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.PrimeDriveBackend.dto.UserDto;
import com.example.PrimeDriveBackend.dto.UserSafeDto;
import com.example.PrimeDriveBackend.mapper.UserMapper;
import com.example.PrimeDriveBackend.model.Users;
import com.example.PrimeDriveBackend.repository.UsersRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private Users user;
    private UserDto userDto;
    private UserSafeDto userSafeDto;

    @BeforeEach
    void setUp() {
        user = new Users();
        user.setId("user-1");
        user.setUsername("john");
        user.setPassword("pw");
        user.setRole("ADMIN");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setBirthdate(Date.valueOf("2024-01-01"));
        user.setEMail("john@example.com");
        user.setAddress("Street 1");
        user.setZipCode("1234");
        user.setCity("City");
        user.setCountry("Country");
        user.setPhoneNumber("123456");
        user.setCreatedUser("system");
        user.setCreatedDate(Date.valueOf("2024-01-01"));
        user.setModifiedUser("system");
        user.setModifiedDate(Date.valueOf("2024-01-02"));
        user.setLastLoginDate(Date.valueOf("2024-01-03"));
        user.setLastLoginIp("1.1.1.1");

        userDto = new UserDto();
        userDto.setId("user-1");
        userDto.setUsername("john");
        userDto.setPassword("pw");
        userDto.setRole("ADMIN");
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setBirthdate(Date.valueOf("2024-01-01"));
        userDto.setEMail("john@example.com");
        userDto.setAddress("Street 1");
        userDto.setZipCode("1234");
        userDto.setCity("City");
        userDto.setCountry("Country");
        userDto.setPhoneNumber("123456");
        userDto.setCreatedUser("system");
        userDto.setCreatedDate(Date.valueOf("2024-01-01"));
        userDto.setModifiedUser("system");
        userDto.setModifiedDate(Date.valueOf("2024-01-02"));
        userDto.setLastLogin(Date.valueOf("2024-01-03"));
        userDto.setLastLoginIp("1.1.1.1");

        userSafeDto = new UserSafeDto();
        userSafeDto.setId("user-1");
        userSafeDto.setUsername("john");
        userSafeDto.setRole("ADMIN");
        userSafeDto.setFirstName("John");
        userSafeDto.setLastName("Doe");
        userSafeDto.setBirthdate(Date.valueOf("2024-01-01"));
        userSafeDto.setEMail("john@example.com");
        userSafeDto.setAddress("Street 1");
        userSafeDto.setZipCode("1234");
        userSafeDto.setCity("City");
        userSafeDto.setCountry("Country");
        userSafeDto.setPhoneNumber("123456");
        userSafeDto.setCreatedUser("system");
        userSafeDto.setCreatedDate(Date.valueOf("2024-01-01"));
        userSafeDto.setModifiedUser("system");
        userSafeDto.setModifiedDate(Date.valueOf("2024-01-02"));
        userSafeDto.setLastLogin(Date.valueOf("2024-01-03"));
        userSafeDto.setLastLoginIp("1.1.1.1");
    }

    @Test
    void existsByUsernameDelegatesToRepository() {
        when(usersRepository.existsByUsername("john")).thenReturn(true);

        boolean result = userService.existsByUsername("john");

        assertEquals(true, result);
        verify(usersRepository).existsByUsername("john");
    }

    @Test
    void findByUsernameReturnsOptional() {
        when(usersRepository.findByUsername("john")).thenReturn(Optional.of(user));

        Optional<Users> result = userService.findByUsername("john");

        assertEquals(Optional.of(user), result);
    }

    @Test
    void saveDelegatesToRepository() {
        when(usersRepository.save(user)).thenReturn(user);

        Users result = userService.save(user);

        assertSame(user, result);
        verify(usersRepository).save(user);
    }

    @Test
    void findByIdReturnsOptional() {
        when(usersRepository.findById("user-1")).thenReturn(Optional.of(user));

        Optional<Users> result = userService.findById("user-1");

        assertEquals(Optional.of(user), result);
    }

    @Test
    void getByIdEntityThrowsWhenMissing() {
        when(usersRepository.findById("missing")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.getByIdEntity("missing"));
    }

    @Test
    void getAllUsersSafeMapsDtos() {
        when(usersRepository.findAll()).thenReturn(List.of(user));
        when(userMapper.toSafeDto(user)).thenReturn(userSafeDto);

        List<UserSafeDto> result = userService.getAllUsersSafe();

        assertEquals(1, result.size());
        assertEquals(userSafeDto, result.get(0));
    }

    @Test
    void getUserByIdSafeReturnsDto() {
        when(usersRepository.findById("user-1")).thenReturn(Optional.of(user));
        when(userMapper.toSafeDto(user)).thenReturn(userSafeDto);

        UserSafeDto result = userService.getUserByIdSafe("user-1");

        assertEquals(userSafeDto, result);
    }

    @Test
    void getUserByIdSafeThrowsWhenMissing() {
        when(usersRepository.findById("missing")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.getUserByIdSafe("missing"));
    }

    @Test
    void createUserMapsAndPersists() {
        when(userMapper.toEntity(userDto)).thenReturn(user);
        when(usersRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDto);

        UserDto result = userService.createUser(userDto);

        assertEquals(userDto, result);
        verify(usersRepository).save(user);
    }

    @Test
    void updateUserMapsAndPersists() {
        Users updated = new Users();
        updated.setId("other");

        when(usersRepository.findById("user-1")).thenReturn(Optional.of(user));
        when(userMapper.toEntity(userDto)).thenReturn(updated);
        when(usersRepository.save(updated)).thenReturn(updated);
        when(userMapper.toDto(updated)).thenReturn(userDto);

        UserDto result = userService.updateUser("user-1", userDto);

        assertEquals(userDto, result);
        assertEquals("user-1", updated.getId());
        verify(usersRepository).save(updated);
    }

    @Test
    void deleteUserThrowsWhenMissing() {
        when(usersRepository.existsById("missing")).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> userService.deleteUser("missing"));
    }

    @Test
    void deleteUserDeletesWhenFound() {
        when(usersRepository.existsById("user-1")).thenReturn(true);

        userService.deleteUser("user-1");

        verify(usersRepository).deleteById("user-1");
    }
}

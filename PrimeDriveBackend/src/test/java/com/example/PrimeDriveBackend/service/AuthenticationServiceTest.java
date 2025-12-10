package com.example.PrimeDriveBackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.PrimeDriveBackend.exception.UnauthorizedAccessException;
import com.example.PrimeDriveBackend.model.Users;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void registerUserSavesEncodedUser() {
        when(userService.existsByUsername("john")).thenReturn(false);
        when(passwordEncoder.encode("pw")).thenReturn("enc");

        authenticationService.registerUser(
                "john",
                "pw",
                "USER",
                "john@example.com",
                Date.valueOf("2024-01-01"),
                "John",
                "Doe",
                "Street 1",
                "1234",
                "City",
                "Country",
                "555",
                "127.0.0.1");

        ArgumentCaptor<Users> captor = ArgumentCaptor.forClass(Users.class);
        verify(userService).save(captor.capture());
        Users saved = captor.getValue();
        assertEquals("john", saved.getUsername());
        assertEquals("enc", saved.getPassword());
        assertEquals("USER", saved.getRole());
        assertEquals("john@example.com", saved.getEMail());
        assertEquals("John", saved.getFirstName());
        assertEquals("Doe", saved.getLastName());
        assertEquals("Street 1", saved.getAddress());
        assertEquals("1234", saved.getZipCode());
        assertEquals("City", saved.getCity());
        assertEquals("Country", saved.getCountry());
        assertEquals("555", saved.getPhoneNumber());
        assertEquals("john", saved.getCreatedUser());
        assertEquals("john", saved.getModifiedUser());
        assertEquals("127.0.0.1", saved.getLastLoginIp());
        assertNotNull(saved.getCreatedDate());
        assertNotNull(saved.getModifiedDate());
        assertNotNull(saved.getLastLoginDate());
    }

    @Test
    void registerUserThrowsWhenUsernameExists() {
        when(userService.existsByUsername("john")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> authenticationService.registerUser(
                "john",
                "pw",
                "USER",
                "john@example.com",
                Date.valueOf("2024-01-01"),
                "John",
                "Doe",
                "Street 1",
                "1234",
                "City",
                "Country",
                "555",
                "127.0.0.1"));
    }

    @Test
    void comparePasswordReturnsTrueWhenMatches() {
        when(passwordEncoder.matches("raw", "enc")).thenReturn(true);

        assertTrue(authenticationService.comparePassword("raw", "enc"));
        verify(passwordEncoder).matches("raw", "enc");
    }

    @Test
    void comparePasswordThrowsWhenMismatch() {
        when(passwordEncoder.matches("raw", "enc")).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> authenticationService.comparePassword("raw", "enc"));
    }

    @Test
    void loginReturnsTrueWhenCredentialsValid() {
        Users user = new Users();
        user.setPassword("enc");
        when(userService.findByUsername("john")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("pw", "enc")).thenReturn(true);

        assertTrue(authenticationService.login("john", "pw"));
    }

    @Test
    void loginThrowsWhenUserMissing() {
        when(userService.findByUsername("john")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> authenticationService.login("john", "pw"));
    }

    @Test
    void loginThrowsWhenPasswordInvalid() {
        Users user = new Users();
        user.setPassword("enc");
        when(userService.findByUsername("john")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("pw", "enc")).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> authenticationService.login("john", "pw"));
    }

    @Test
    void checkAuthenticationReturnsJwtSubject() {
        Jwt jwt = Jwt.withTokenValue("t").header("alg", "none").claim("sub", "user-1").build();
        Authentication authentication = org.mockito.Mockito.mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn(jwt);

        String result = authenticationService.checkAuthentication(authentication);

        assertEquals("user-1", result);
    }

    @Test
    void checkAuthenticationReturnsNameForNonJwt() {
        Authentication authentication = org.mockito.Mockito.mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn("principal");
        when(authentication.getName()).thenReturn("user-2");

        String result = authenticationService.checkAuthentication(authentication);

        assertEquals("user-2", result);
    }

    @Test
    void checkAuthenticationThrowsWhenUnauthenticated() {
        Authentication authentication = org.mockito.Mockito.mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(false);

        assertThrows(UnauthorizedAccessException.class,
                () -> authenticationService.checkAuthentication(authentication));
    }

    @Test
    void checkAuthenticationThrowsWhenNull() {
        assertThrows(UnauthorizedAccessException.class, () -> authenticationService.checkAuthentication(null));
    }
}

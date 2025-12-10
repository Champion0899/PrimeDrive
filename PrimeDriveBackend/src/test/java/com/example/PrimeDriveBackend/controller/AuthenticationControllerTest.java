package com.example.PrimeDriveBackend.controller;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import com.example.PrimeDriveBackend.dto.LoginRequestDto;
import com.example.PrimeDriveBackend.dto.RegisterRequestDto;
import com.example.PrimeDriveBackend.filter.JwtFilter;
import com.example.PrimeDriveBackend.model.Users;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.RequestInfoService;
import com.example.PrimeDriveBackend.service.UserService;
import com.example.PrimeDriveBackend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthenticationController authenticationController;

    @MockitoBean
    private AuthenticationService authenticationService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private RequestInfoService requestInfoService;

    @MockitoBean
    private JwtFilter jwtFilter;

    private RegisterRequestDto registerRequest;
    private LoginRequestDto loginRequest;

    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequestDto();
        registerRequest.setUsername("john");
        registerRequest.setPassword("pw");
        registerRequest.setRole("ADMIN");
        registerRequest.setEmail("john@example.com");
        registerRequest.setBirthdate(Date.valueOf("2024-01-01"));
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setAddress("Street");
        registerRequest.setZipCode("1234");
        registerRequest.setCity("City");
        registerRequest.setCountry("Country");
        registerRequest.setPhoneNumber("555");

        loginRequest = new LoginRequestDto();
        loginRequest.setUsername("john");
        loginRequest.setPassword("pw");

        // reset feature flag for each test
        ReflectionTestUtils.setField(authenticationController, "swaggerSetupEnabled", true);
    }

    @Test
    void registerUserReturnsMessageAndDelegates() throws Exception {
        when(requestInfoService.getClientIp(any())).thenReturn("10.0.0.1");

        mockMvc.perform(post("/api/authentication/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User registered successfully"));

        org.mockito.ArgumentCaptor<Date> dateCaptor = org.mockito.ArgumentCaptor.forClass(Date.class);

        verify(authenticationService).registerUser(
                eq("john"),
                eq("pw"),
                eq("USER"),
                eq("john@example.com"),
                dateCaptor.capture(),
                eq("John"),
                eq("Doe"),
                eq("Street"),
                eq("1234"),
                eq("City"),
                eq("Country"),
                eq("555"),
                eq("10.0.0.1"));

        assertNotNull(dateCaptor.getValue());
    }

    @Test
    void loginSuccessSetsCookieAndReturnsMessage() throws Exception {
        Users user = new Users();
        user.setId("user-1");
        when(authenticationService.login("john", "pw")).thenReturn(true);
        when(userService.findByUsername("john")).thenReturn(Optional.of(user));
        when(jwtUtil.generateToken("user-1")).thenReturn("token123");

        mockMvc.perform(post("/api/authentication/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(header().string("Set-Cookie", containsString("jwt=token123")))
                .andExpect(jsonPath("$.message").value("Login successful"));
    }

    @Test
    void loginFailureReturnsUnauthorized() throws Exception {
        when(authenticationService.login("john", "pw")).thenReturn(false);

        mockMvc.perform(post("/api/authentication/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));
    }

    @Test
    void logoutClearsJwtCookie() throws Exception {
        mockMvc.perform(post("/api/authentication/logout"))
                .andExpect(status().isOk())
                .andExpect(header().string("Set-Cookie", containsString("jwt=")))
                .andExpect(jsonPath("$.message").value("Logged out"));
    }

    @Test
    void swaggerRegisterDisabledReturnsForbidden() throws Exception {
        ReflectionTestUtils.setField(authenticationController, "swaggerSetupEnabled", false);

        mockMvc.perform(post("/api/authentication/swagger-register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isForbidden())
                .andExpect(content().string(containsString("disabled")));
    }

    @Test
    void swaggerRegisterRejectsNonAdminRole() throws Exception {
        registerRequest.setRole("USER");

        mockMvc.perform(post("/api/authentication/swagger-register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isForbidden());
    }

    @Test
    void swaggerRegisterCreatesAdminWhenEnabled() throws Exception {
        when(requestInfoService.getClientIp(any())).thenReturn("10.0.0.1");

        mockMvc.perform(post("/api/authentication/swagger-register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Admin user registered successfully")));

        org.mockito.ArgumentCaptor<Date> dateCaptor = org.mockito.ArgumentCaptor.forClass(Date.class);

        verify(authenticationService).registerUser(
                eq("john"),
                eq("pw"),
                eq("ADMIN"),
                eq("john@example.com"),
                dateCaptor.capture(),
                eq("John"),
                eq("Doe"),
                eq("Street"),
                eq("1234"),
                eq("City"),
                eq("Country"),
                eq("555"),
                eq("10.0.0.1"));

        assertNotNull(dateCaptor.getValue());
    }

    @Test
    void swaggerLoginDisabledReturnsForbidden() throws Exception {
        ReflectionTestUtils.setField(authenticationController, "swaggerSetupEnabled", false);

        mockMvc.perform(post("/api/authentication/swagger-login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isForbidden())
                .andExpect(content().string(containsString("disabled")));
    }

    @Test
    void swaggerLoginReturnsTokenWhenEnabled() throws Exception {
        Users user = new Users();
        user.setId("user-1");
        when(authenticationService.login("john", "pw")).thenReturn(true);
        when(userService.findByUsername("john")).thenReturn(Optional.of(user));
        when(jwtUtil.generateToken("user-1")).thenReturn("token123");

        mockMvc.perform(post("/api/authentication/swagger-login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("user-1"))
                .andExpect(jsonPath("$.token").value("token123"));
    }

    @Test
    void swaggerLoginReturnsUnauthorizedOnFailure() throws Exception {
        when(authenticationService.login("john", "pw")).thenReturn(false);

        mockMvc.perform(post("/api/authentication/swagger-login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));
    }

    @Test
    @WithMockUser
    void checkSessionReturnsTrueWhenAuthenticated() throws Exception {
        mockMvc.perform(get("/api/authentication/check-session"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void checkSessionReturnsFalseWhenAnonymous() throws Exception {
        org.springframework.security.core.context.SecurityContextHolder.clearContext();

        mockMvc.perform(get("/api/authentication/check-session"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}

package com.example.PrimeDriveBackend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.PrimeDriveBackend.dto.UserDto;
import com.example.PrimeDriveBackend.dto.UserSafeDto;
import com.example.PrimeDriveBackend.filter.JwtFilter;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.UserService;
import com.example.PrimeDriveBackend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UsersController.class)
@AutoConfigureMockMvc(addFilters = false)
class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private AuthenticationService authenticationService;

    @MockitoBean
    private JwtFilter jwtFilter;

    @MockitoBean
    private JwtUtil jwtUtil;

    private UserDto userDto;
    private UserSafeDto userSafeDto;

    @BeforeEach
    void setUp() {
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
    @WithMockUser(roles = { "ADMIN" })
    void getAllUsersReturnsListForAdmin() throws Exception {
        when(userService.getAllUsersSafe()).thenReturn(List.of(userSafeDto));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("user-1"))
                .andExpect(jsonPath("$[0].username").value("john"));

        verify(userService).getAllUsersSafe();
    }

    @Test
    @WithMockUser
    void getUserByIdReturnsSafeDto() throws Exception {
        when(userService.getUserByIdSafe("user-1")).thenReturn(userSafeDto);

        mockMvc.perform(get("/api/users/{id}", "user-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("user-1"))
                .andExpect(jsonPath("$.username").value("john"));

        verify(userService).getUserByIdSafe("user-1");
    }

    @Test
    @WithMockUser
    void getCurrentUserReturnsSafeDto() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("user-1");
        when(userService.getUserByIdSafe("user-1")).thenReturn(userSafeDto);

        mockMvc.perform(get("/api/users/current"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("user-1"));

        verify(authenticationService).checkAuthentication(any());
        verify(userService).getUserByIdSafe("user-1");
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void createUserPersistsForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(userService.createUser(any(UserDto.class))).thenReturn(userDto);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("user-1"));

        verify(authenticationService).checkAuthentication(any());
        verify(userService).createUser(any(UserDto.class));
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void updateUserModifiesForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(userService.updateUser(org.mockito.ArgumentMatchers.eq("user-1"), any(UserDto.class)))
                .thenReturn(userDto);

        mockMvc.perform(put("/api/users/{id}", "user-1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("user-1"));

        verify(authenticationService).checkAuthentication(any());
        verify(userService).updateUser(org.mockito.ArgumentMatchers.eq("user-1"), any(UserDto.class));
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void deleteUserRemovesForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");

        mockMvc.perform(delete("/api/users/{id}", "user-1"))
                .andExpect(status().isOk());

        verify(authenticationService).checkAuthentication(any());
        verify(userService).deleteUser("user-1");
    }
}

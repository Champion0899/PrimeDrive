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

import com.example.PrimeDriveBackend.dto.VehicleEngineDto;
import com.example.PrimeDriveBackend.filter.JwtFilter;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleEngineService;
import com.example.PrimeDriveBackend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(VehicleEngineController.class)
@AutoConfigureMockMvc(addFilters = false)
class VehicleEngineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private VehicleEngineService vehicleEngineService;

    @MockitoBean
    private AuthenticationService authenticationService;

    @MockitoBean
    private JwtFilter jwtFilter;

    @MockitoBean
    private JwtUtil jwtUtil;

    private VehicleEngineDto engineDto;

    @BeforeEach
    void setUp() {
        engineDto = new VehicleEngineDto();
        engineDto.setId("eng-1");
        engineDto.setEngineType("Petrol");
    }

    @Test
    @WithMockUser
    void listAllReturnsEngines() throws Exception {
        when(vehicleEngineService.getAllEngines()).thenReturn(List.of(engineDto));

        mockMvc.perform(get("/api/vehicle_engine"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("eng-1"))
                .andExpect(jsonPath("$[0].engineType").value("Petrol"));

        verify(vehicleEngineService).getAllEngines();
    }

    @Test
    @WithMockUser
    void getByIdReturnsEngine() throws Exception {
        when(vehicleEngineService.getEngineById("eng-1")).thenReturn(engineDto);

        mockMvc.perform(get("/api/vehicle_engine/{id}", "eng-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("eng-1"));

        verify(vehicleEngineService).getEngineById("eng-1");
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void createPersistsEngineForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(vehicleEngineService.saveEngine(any(VehicleEngineDto.class))).thenReturn(engineDto);

        mockMvc.perform(post("/api/vehicle_engine")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(engineDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("eng-1"));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleEngineService).saveEngine(any(VehicleEngineDto.class));
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void updateModifiesEngineForAdmin() throws Exception {
        VehicleEngineDto updated = new VehicleEngineDto();
        updated.setId("eng-1");
        updated.setEngineType("Diesel");

        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(vehicleEngineService.updateEngine("eng-1", updated)).thenReturn(updated);

        mockMvc.perform(put("/api/vehicle_engine/{id}", "eng-1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.engineType").value("Diesel"));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleEngineService).updateEngine("eng-1", updated);
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void deleteRemovesEngineForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");

        mockMvc.perform(delete("/api/vehicle_engine/{id}", "eng-1"))
                .andExpect(status().isOk());

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleEngineService).deleteEngine("eng-1");
    }
}

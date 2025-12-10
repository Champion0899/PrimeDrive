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

import com.example.PrimeDriveBackend.dto.VehicleSeatsDto;
import com.example.PrimeDriveBackend.filter.JwtFilter;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleSeatsService;
import com.example.PrimeDriveBackend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(VehicleSeatsController.class)
@AutoConfigureMockMvc(addFilters = false)
class VehicleSeatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private VehicleSeatsService vehicleSeatsService;

    @MockitoBean
    private AuthenticationService authenticationService;

    @MockitoBean
    private JwtFilter jwtFilter;

    @MockitoBean
    private JwtUtil jwtUtil;

    private VehicleSeatsDto seatsDto;

    @BeforeEach
    void setUp() {
        seatsDto = new VehicleSeatsDto();
        seatsDto.setId("seat-1");
        seatsDto.setQuantity(5);
    }

    @Test
    @WithMockUser
    void listAllReturnsSeats() throws Exception {
        when(vehicleSeatsService.getAllSeats()).thenReturn(List.of(seatsDto));

        mockMvc.perform(get("/api/vehicle_seats"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("seat-1"))
                .andExpect(jsonPath("$[0].quantity").value(5));

        verify(vehicleSeatsService).getAllSeats();
    }

    @Test
    @WithMockUser
    void getByIdReturnsSeats() throws Exception {
        when(vehicleSeatsService.getSeatsById("seat-1")).thenReturn(seatsDto);

        mockMvc.perform(get("/api/vehicle_seats/{id}", "seat-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("seat-1"));

        verify(vehicleSeatsService).getSeatsById("seat-1");
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void createPersistsSeatsForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(vehicleSeatsService.saveSeats(any(VehicleSeatsDto.class))).thenReturn(seatsDto);

        mockMvc.perform(post("/api/vehicle_seats")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(seatsDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(5));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleSeatsService).saveSeats(any(VehicleSeatsDto.class));
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void updateModifiesSeatsForAdmin() throws Exception {
        VehicleSeatsDto updated = new VehicleSeatsDto();
        updated.setId("seat-1");
        updated.setQuantity(7);

        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(vehicleSeatsService.updateSeats("seat-1", updated)).thenReturn(updated);

        mockMvc.perform(put("/api/vehicle_seats/{id}", "seat-1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(7));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleSeatsService).updateSeats("seat-1", updated);
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void deleteRemovesSeatsForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");

        mockMvc.perform(delete("/api/vehicle_seats/{id}", "seat-1"))
                .andExpect(status().isOk());

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleSeatsService).deleteSeats("seat-1");
    }
}

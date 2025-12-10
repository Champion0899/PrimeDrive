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

import com.example.PrimeDriveBackend.dto.VehicleHoldingsDto;
import com.example.PrimeDriveBackend.filter.JwtFilter;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleHoldingsService;
import com.example.PrimeDriveBackend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(VehicleHoldingsController.class)
@AutoConfigureMockMvc(addFilters = false)
class VehicleHoldingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private VehicleHoldingsService vehicleHoldingsService;

    @MockitoBean
    private AuthenticationService authenticationService;

    @MockitoBean
    private JwtFilter jwtFilter;

    @MockitoBean
    private JwtUtil jwtUtil;

    private VehicleHoldingsDto holdingDto;

    @BeforeEach
    void setUp() {
        holdingDto = new VehicleHoldingsDto();
        holdingDto.setId("holding-1");
        holdingDto.setName("VW");
        holdingDto.setFounding(2020);
        holdingDto.setLogo("logo.png");
    }

    @Test
    @WithMockUser
    void listAllReturnsHoldings() throws Exception {
        when(vehicleHoldingsService.getAllHoldings()).thenReturn(List.of(holdingDto));

        mockMvc.perform(get("/api/vehicle_holdings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("holding-1"))
                .andExpect(jsonPath("$[0].name").value("VW"));

        verify(vehicleHoldingsService).getAllHoldings();
    }

    @Test
    @WithMockUser
    void getByIdReturnsHolding() throws Exception {
        when(vehicleHoldingsService.getHoldingById("holding-1")).thenReturn(holdingDto);

        mockMvc.perform(get("/api/vehicle_holdings/{id}", "holding-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("holding-1"))
                .andExpect(jsonPath("$.name").value("VW"));

        verify(vehicleHoldingsService).getHoldingById("holding-1");
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void createPersistsHoldingForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(vehicleHoldingsService.saveHolding(any(VehicleHoldingsDto.class))).thenReturn(holdingDto);

        mockMvc.perform(post("/api/vehicle_holdings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(holdingDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("holding-1"));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleHoldingsService).saveHolding(any(VehicleHoldingsDto.class));
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void updateModifiesHoldingForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(vehicleHoldingsService.updateHolding("holding-1", holdingDto)).thenReturn(holdingDto);

        mockMvc.perform(put("/api/vehicle_holdings/{id}", "holding-1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(holdingDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("holding-1"));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleHoldingsService).updateHolding("holding-1", holdingDto);
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void deleteRemovesHoldingForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");

        mockMvc.perform(delete("/api/vehicle_holdings/{id}", "holding-1"))
                .andExpect(status().isNoContent());

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleHoldingsService).deleteHolding("holding-1");
    }
}

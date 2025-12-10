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

import com.example.PrimeDriveBackend.dto.VehicleFuelsDto;
import com.example.PrimeDriveBackend.filter.JwtFilter;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleFuelsService;
import com.example.PrimeDriveBackend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(VehicleFuelsController.class)
@AutoConfigureMockMvc(addFilters = false)
class VehicleFuelsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private VehicleFuelsService vehicleFuelsService;

    @MockitoBean
    private AuthenticationService authenticationService;

    @MockitoBean
    private JwtFilter jwtFilter;

    @MockitoBean
    private JwtUtil jwtUtil;

    private VehicleFuelsDto fuelsDto;

    @BeforeEach
    void setUp() {
        fuelsDto = new VehicleFuelsDto();
        fuelsDto.setId("fuel-1");
        fuelsDto.setFuelType("Diesel");
    }

    @Test
    @WithMockUser
    void listAllReturnsFuels() throws Exception {
        when(vehicleFuelsService.getFuelTypes()).thenReturn(List.of(fuelsDto));

        mockMvc.perform(get("/api/vehicle_fuels"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("fuel-1"))
                .andExpect(jsonPath("$[0].fuelType").value("Diesel"));

        verify(vehicleFuelsService).getFuelTypes();
    }

    @Test
    @WithMockUser
    void getByIdReturnsFuel() throws Exception {
        when(vehicleFuelsService.getFuelsById("fuel-1")).thenReturn(fuelsDto);

        mockMvc.perform(get("/api/vehicle_fuels/{id}", "fuel-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("fuel-1"));

        verify(vehicleFuelsService).getFuelsById("fuel-1");
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void createPersistsFuelForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(vehicleFuelsService.saveFuels(any(VehicleFuelsDto.class))).thenReturn(fuelsDto);

        mockMvc.perform(post("/api/vehicle_fuels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(fuelsDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fuelType").value("Diesel"));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleFuelsService).saveFuels(any(VehicleFuelsDto.class));
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void updateModifiesFuelForAdmin() throws Exception {
        VehicleFuelsDto updated = new VehicleFuelsDto();
        updated.setId("fuel-1");
        updated.setFuelType("Hybrid");

        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(vehicleFuelsService.updateFuels("fuel-1", updated)).thenReturn(updated);

        mockMvc.perform(put("/api/vehicle_fuels/{id}", "fuel-1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fuelType").value("Hybrid"));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleFuelsService).updateFuels("fuel-1", updated);
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void deleteRemovesFuelForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");

        mockMvc.perform(delete("/api/vehicle_fuels/{id}", "fuel-1"))
                .andExpect(status().isOk());

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleFuelsService).deleteFuels("fuel-1");
    }
}

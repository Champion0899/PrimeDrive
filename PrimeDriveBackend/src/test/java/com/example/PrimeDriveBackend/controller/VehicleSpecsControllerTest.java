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

import com.example.PrimeDriveBackend.dto.VehicleSpecsDto;
import com.example.PrimeDriveBackend.filter.JwtFilter;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleSpecsService;
import com.example.PrimeDriveBackend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(VehicleSpecsController.class)
@AutoConfigureMockMvc(addFilters = false)
class VehicleSpecsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private VehicleSpecsService vehicleSpecsService;

    @MockitoBean
    private AuthenticationService authenticationService;

    @MockitoBean
    private JwtFilter jwtFilter;

    @MockitoBean
    private JwtUtil jwtUtil;

    private VehicleSpecsDto specsDto;

    @BeforeEach
    void setUp() {
        specsDto = new VehicleSpecsDto();
        specsDto.setId("spec-1");
        specsDto.setPowerKw(100);
        specsDto.setPowerPs(120);
        specsDto.setLengthMillimeter(4500);
        specsDto.setWidthMillimeter(1800);
        specsDto.setHeightMillimeter(1400);
        specsDto.setTrunkInLiterMin(300);
        specsDto.setTrunkInLiterMax(500);
        specsDto.setZeroToHundredInSeconds(7.5);
        specsDto.setTopSpeedInKmh(200);
        specsDto.setConsumptionHundredInX(6.5);
        specsDto.setCoTwoEmissionInGPerKm(100);
        specsDto.setCubicCapacity(2.0);
        specsDto.setDoorsId("door-1");
        specsDto.setSeatsId("seat-1");
        specsDto.setEngineId("eng-1");
        specsDto.setFuelsId("fuel-1");
    }

    @Test
    @WithMockUser
    void listAllReturnsSpecs() throws Exception {
        when(vehicleSpecsService.getAllSpecs()).thenReturn(List.of(specsDto));

        mockMvc.perform(get("/api/vehicle_specs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("spec-1"))
                .andExpect(jsonPath("$[0].powerKw").value(100));

        verify(vehicleSpecsService).getAllSpecs();
    }

    @Test
    @WithMockUser
    void getByIdReturnsSingleSpecs() throws Exception {
        when(vehicleSpecsService.getSpecsById("spec-1")).thenReturn(specsDto);

        mockMvc.perform(get("/api/vehicle_specs/{id}", "spec-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("spec-1"))
                .andExpect(jsonPath("$.powerKw").value(100));

        verify(vehicleSpecsService).getSpecsById("spec-1");
    }

    @Test
    @WithMockUser(roles = { "SELLER" })
    void createPersistsSpecsForSellerOrAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("seller-1");
        when(vehicleSpecsService.saveSpecs(any(VehicleSpecsDto.class))).thenReturn(specsDto);

        mockMvc.perform(post("/api/vehicle_specs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(specsDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("spec-1"));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleSpecsService).saveSpecs(any(VehicleSpecsDto.class));
    }

    @Test
    @WithMockUser(roles = { "SELLER" })
    void updateModifiesSpecsForSellerOrAdmin() throws Exception {
        VehicleSpecsDto updated = new VehicleSpecsDto();
        updated.setId("spec-1");
        updated.setPowerKw(110);
        updated.setPowerPs(125);
        updated.setLengthMillimeter(4500);
        updated.setWidthMillimeter(1800);
        updated.setHeightMillimeter(1400);
        updated.setTrunkInLiterMin(300);
        updated.setTrunkInLiterMax(500);
        updated.setZeroToHundredInSeconds(7.4);
        updated.setTopSpeedInKmh(205);
        updated.setConsumptionHundredInX(6.4);
        updated.setCoTwoEmissionInGPerKm(98);
        updated.setCubicCapacity(2.0);
        updated.setDoorsId("door-1");
        updated.setSeatsId("seat-1");
        updated.setEngineId("eng-1");
        updated.setFuelsId("fuel-1");

        when(authenticationService.checkAuthentication(any())).thenReturn("seller-1");
        when(vehicleSpecsService.updateSpecs("spec-1", updated)).thenReturn(updated);

        mockMvc.perform(put("/api/vehicle_specs/{id}", "spec-1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.powerKw").value(110));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleSpecsService).updateSpecs("spec-1", updated);
    }

    @Test
    @WithMockUser(roles = { "SELLER" })
    void deleteRemovesSpecsForSellerOrAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("seller-1");

        mockMvc.perform(delete("/api/vehicle_specs/{id}", "spec-1"))
                .andExpect(status().isOk());

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleSpecsService).deleteSpecs("spec-1");
    }
}

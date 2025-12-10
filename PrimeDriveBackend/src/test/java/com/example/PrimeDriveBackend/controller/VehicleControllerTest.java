package com.example.PrimeDriveBackend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

import com.example.PrimeDriveBackend.dto.VehicleDto;
import com.example.PrimeDriveBackend.filter.JwtFilter;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleService;
import com.example.PrimeDriveBackend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(VehicleController.class)
@AutoConfigureMockMvc(addFilters = false)
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private VehicleService vehicleService;

    @MockitoBean
    private AuthenticationService authenticationService;

    @MockitoBean
    private JwtFilter jwtFilter;

    @MockitoBean
    private JwtUtil jwtUtil;

    private VehicleDto vehicleDto;

    @BeforeEach
    void setUp() {
        vehicleDto = new VehicleDto();
        vehicleDto.setId("veh-1");
        vehicleDto.setName("Car");
        vehicleDto.setPrice(10000d);
        vehicleDto.setYear(2020);
        vehicleDto.setImage("https://img");
        vehicleDto.setMileage(1000);
        vehicleDto.setCondition("new");
        vehicleDto.setVehicleHistory("clean");
        vehicleDto.setBrandsId("b1");
        vehicleDto.setSpecsId("s1");
        vehicleDto.setTypesId("t1");
        vehicleDto.setColorsId("c1");
        vehicleDto.setSellerId("u1");
    }

    @Test
    @WithMockUser
    void listAllReturnsAllVehicles() throws Exception {
        when(vehicleService.getAllVehicles()).thenReturn(List.of(vehicleDto));

        mockMvc.perform(get("/api/vehicle"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("veh-1"))
                .andExpect(jsonPath("$[0].name").value("Car"));

        verify(vehicleService).getAllVehicles();
    }

    @Test
    @WithMockUser
    void getByIdReturnsSingleVehicle() throws Exception {
        when(vehicleService.getVehicleById("veh-1")).thenReturn(vehicleDto);

        mockMvc.perform(get("/api/vehicle/{id}", "veh-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("veh-1"))
                .andExpect(jsonPath("$.name").value("Car"));

        verify(vehicleService).getVehicleById("veh-1");
    }

    @Test
    @WithMockUser(roles = { "SELLER" })
    void createPersistsVehicleForSellerOrAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("u1");
        when(vehicleService.saveVehicle(any(VehicleDto.class))).thenReturn(vehicleDto);

        mockMvc.perform(post("/api/vehicle")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicleDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("veh-1"))
                .andExpect(jsonPath("$.name").value("Car"));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleService).saveVehicle(any(VehicleDto.class));
    }

    @Test
    @WithMockUser(roles = { "SELLER" })
    void updateModifiesVehicleForSellerOrAdmin() throws Exception {
        VehicleDto updated = new VehicleDto();
        updated.setId("veh-1");
        updated.setName("Car2");
        updated.setPrice(12000d);
        updated.setYear(2021);
        updated.setImage("https://img2");
        updated.setMileage(500);
        updated.setCondition("used");
        updated.setVehicleHistory("clean");
        updated.setBrandsId("b1");
        updated.setSpecsId("s1");
        updated.setTypesId("t1");
        updated.setColorsId("c1");
        updated.setSellerId("u1");

        when(authenticationService.checkAuthentication(any())).thenReturn("u1");
        when(vehicleService.updateVehicle(eq("veh-1"), any(VehicleDto.class))).thenReturn(updated);

        mockMvc.perform(put("/api/vehicle/{id}", "veh-1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Car2"));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleService).updateVehicle(eq("veh-1"), any(VehicleDto.class));
    }

    @Test
    @WithMockUser(roles = { "SELLER" })
    void deleteRemovesVehicleForSellerOrAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("u1");

        mockMvc.perform(delete("/api/vehicle/{id}", "veh-1"))
                .andExpect(status().isOk());

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleService).deleteVehicle("veh-1");
    }
}

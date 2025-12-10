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

import com.example.PrimeDriveBackend.dto.VehicleDoorsDto;
import com.example.PrimeDriveBackend.filter.JwtFilter;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleDoorsService;
import com.example.PrimeDriveBackend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(VehicleDoorsController.class)
@AutoConfigureMockMvc(addFilters = false)
class VehicleDoorsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private VehicleDoorsService vehicleDoorsService;

    @MockitoBean
    private AuthenticationService authenticationService;

    @MockitoBean
    private JwtFilter jwtFilter;

    @MockitoBean
    private JwtUtil jwtUtil;

    private VehicleDoorsDto doorsDto;

    @BeforeEach
    void setUp() {
        doorsDto = new VehicleDoorsDto();
        doorsDto.setId("door-1");
        doorsDto.setQuantity(4);
    }

    @Test
    @WithMockUser
    void listAllReturnsDoors() throws Exception {
        when(vehicleDoorsService.getAllDoors()).thenReturn(List.of(doorsDto));

        mockMvc.perform(get("/api/vehicle_doors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("door-1"))
                .andExpect(jsonPath("$[0].quantity").value(4));

        verify(vehicleDoorsService).getAllDoors();
    }

    @Test
    @WithMockUser
    void getByIdReturnsDoors() throws Exception {
        when(vehicleDoorsService.getDoorsById("door-1")).thenReturn(doorsDto);

        mockMvc.perform(get("/api/vehicle_doors/{id}", "door-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("door-1"));

        verify(vehicleDoorsService).getDoorsById("door-1");
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void createPersistsDoorsForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(vehicleDoorsService.saveDoors(any(VehicleDoorsDto.class))).thenReturn(doorsDto);

        mockMvc.perform(post("/api/vehicle_doors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(doorsDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(4));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleDoorsService).saveDoors(any(VehicleDoorsDto.class));
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void updateModifiesDoorsForAdmin() throws Exception {
        VehicleDoorsDto updated = new VehicleDoorsDto();
        updated.setId("door-1");
        updated.setQuantity(2);

        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(vehicleDoorsService.updateDoors("door-1", updated)).thenReturn(updated);

        mockMvc.perform(put("/api/vehicle_doors/{id}", "door-1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(2));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleDoorsService).updateDoors("door-1", updated);
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void deleteRemovesDoorsForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");

        mockMvc.perform(delete("/api/vehicle_doors/{id}", "door-1"))
                .andExpect(status().isOk());

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleDoorsService).deleteDoors("door-1");
    }
}

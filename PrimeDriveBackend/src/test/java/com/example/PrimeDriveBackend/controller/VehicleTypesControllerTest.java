package com.example.PrimeDriveBackend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
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

import com.example.PrimeDriveBackend.dto.VehicleTypesDto;
import com.example.PrimeDriveBackend.filter.JwtFilter;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleTypesService;
import com.example.PrimeDriveBackend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(VehicleTypesController.class)
@AutoConfigureMockMvc(addFilters = false)
class VehicleTypesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private VehicleTypesService vehicleTypesService;

    @MockitoBean
    private AuthenticationService authenticationService;

    @MockitoBean
    private JwtFilter jwtFilter;

    @MockitoBean
    private JwtUtil jwtUtil;

    private VehicleTypesDto suvDto;
    private VehicleTypesDto sedanDto;

    @BeforeEach
    void setUp() {
        suvDto = new VehicleTypesDto("1111-aaaa", "SUV");
        sedanDto = new VehicleTypesDto("2222-bbbb", "Sedan");
    }

    @Test
    @WithMockUser
    void listAllReturnsAllVehicleTypes() throws Exception {
        when(vehicleTypesService.getAllTypes()).thenReturn(List.of(suvDto, sedanDto));

        mockMvc.perform(get("/api/vehicle_types"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(suvDto.getId()))
                .andExpect(jsonPath("$[0].type").value(suvDto.getType()))
                .andExpect(jsonPath("$[1].id").value(sedanDto.getId()))
                .andExpect(jsonPath("$[1].type").value(sedanDto.getType()));

        verify(vehicleTypesService).getAllTypes();
    }

    @Test
    @WithMockUser
    void getByIdReturnsSingleVehicleType() throws Exception {
        when(vehicleTypesService.getTypeById("1111-aaaa")).thenReturn(suvDto);

        mockMvc.perform(get("/api/vehicle_types/{id}", "1111-aaaa"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(suvDto.getId()))
                .andExpect(jsonPath("$.type").value(suvDto.getType()));

        verify(vehicleTypesService).getTypeById("1111-aaaa");
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createPersistsVehicleTypeForAdmin() throws Exception {
        when(vehicleTypesService.saveType(any(VehicleTypesDto.class))).thenReturn(suvDto);
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-id");

        mockMvc.perform(post("/api/vehicle_types")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(suvDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(suvDto.getId()))
                .andExpect(jsonPath("$.type").value(suvDto.getType()));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleTypesService).saveType(any(VehicleTypesDto.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateModifiesVehicleTypeForAdmin() throws Exception {
        VehicleTypesDto updatedDto = new VehicleTypesDto("1111-aaaa", "Crossover");

        when(vehicleTypesService.updateType(eq("1111-aaaa"), any(VehicleTypesDto.class))).thenReturn(updatedDto);
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-id");

        mockMvc.perform(put("/api/vehicle_types/{id}", "1111-aaaa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(updatedDto.getId()))
                .andExpect(jsonPath("$.type").value(updatedDto.getType()));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleTypesService).updateType(eq("1111-aaaa"), any(VehicleTypesDto.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteRemovesVehicleTypeForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-id");
        doNothing().when(vehicleTypesService).deleteType("1111-aaaa");

        mockMvc.perform(delete("/api/vehicle_types/{id}", "1111-aaaa"))
                .andExpect(status().isNoContent());

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleTypesService).deleteType("1111-aaaa");
    }
}

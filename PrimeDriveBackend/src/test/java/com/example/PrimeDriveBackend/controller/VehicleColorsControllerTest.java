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

import com.example.PrimeDriveBackend.dto.VehicleColorsDto;
import com.example.PrimeDriveBackend.filter.JwtFilter;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleColorsService;
import com.example.PrimeDriveBackend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(VehicleColorsController.class)
@AutoConfigureMockMvc(addFilters = false)
class VehicleColorsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private VehicleColorsService vehicleColorsService;

    @MockitoBean
    private AuthenticationService authenticationService;

    @MockitoBean
    private JwtFilter jwtFilter;

    @MockitoBean
    private JwtUtil jwtUtil;

    private VehicleColorsDto colorDto;

    @BeforeEach
    void setUp() {
        colorDto = new VehicleColorsDto();
        colorDto.setId("color-1");
        colorDto.setName("Red");
        colorDto.setHexCode("FF0000");
    }

    @Test
    @WithMockUser
    void listAllReturnsColors() throws Exception {
        when(vehicleColorsService.getAllColors()).thenReturn(List.of(colorDto));

        mockMvc.perform(get("/api/vehicle_colors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("color-1"))
                .andExpect(jsonPath("$[0].name").value("Red"));

        verify(vehicleColorsService).getAllColors();
    }

    @Test
    @WithMockUser
    void getByIdReturnsColor() throws Exception {
        when(vehicleColorsService.getColorById("color-1")).thenReturn(colorDto);

        mockMvc.perform(get("/api/vehicle_colors/{id}", "color-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("color-1"))
                .andExpect(jsonPath("$.name").value("Red"));

        verify(vehicleColorsService).getColorById("color-1");
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void createPersistsColorForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(vehicleColorsService.saveColor(any(VehicleColorsDto.class))).thenReturn(colorDto);

        mockMvc.perform(post("/api/vehicle_colors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(colorDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("color-1"));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleColorsService).saveColor(any(VehicleColorsDto.class));
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void updateModifiesColorForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(vehicleColorsService.updateColor("color-1", colorDto)).thenReturn(colorDto);

        mockMvc.perform(put("/api/vehicle_colors/{id}", "color-1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(colorDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("color-1"));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleColorsService).updateColor("color-1", colorDto);
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void deleteRemovesColorForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");

        mockMvc.perform(delete("/api/vehicle_colors/{id}", "color-1"))
                .andExpect(status().isOk());

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleColorsService).deleteColor("color-1");
    }
}

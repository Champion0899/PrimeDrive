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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.PrimeDriveBackend.dto.VehicleBrandsDto;
import com.example.PrimeDriveBackend.filter.JwtFilter;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.VehicleBrandsService;
import com.example.PrimeDriveBackend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(VehicleBrandsController.class)
@AutoConfigureMockMvc(addFilters = false)
class VehicleBrandsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VehicleBrandsService vehicleBrandsService;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private JwtFilter jwtFilter;

    @MockBean
    private JwtUtil jwtUtil;

    private VehicleBrandsDto brandDto;

    @BeforeEach
    void setUp() {
        brandDto = new VehicleBrandsDto();
        brandDto.setId("brand-1");
        brandDto.setName("Brand");
        brandDto.setFounding(2020);
        brandDto.setLogo("logo.png");
        brandDto.setHoldingId("holding-1");
    }

    @Test
    @WithMockUser
    void listAllReturnsBrands() throws Exception {
        when(vehicleBrandsService.getAllBrands()).thenReturn(List.of(brandDto));

        mockMvc.perform(get("/api/vehicle_brands"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("brand-1"))
                .andExpect(jsonPath("$[0].name").value("Brand"));

        verify(vehicleBrandsService).getAllBrands();
    }

    @Test
    @WithMockUser
    void getByIdReturnsBrand() throws Exception {
        when(vehicleBrandsService.getBrandById("brand-1")).thenReturn(brandDto);

        mockMvc.perform(get("/api/vehicle_brands/{id}", "brand-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("brand-1"))
                .andExpect(jsonPath("$.name").value("Brand"));

        verify(vehicleBrandsService).getBrandById("brand-1");
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void createPersistsBrandForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(vehicleBrandsService.saveBrand(any(VehicleBrandsDto.class))).thenReturn(brandDto);

        mockMvc.perform(post("/api/vehicle_brands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brandDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("brand-1"));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleBrandsService).saveBrand(any(VehicleBrandsDto.class));
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void updateModifiesBrandForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(vehicleBrandsService.updateBrand("brand-1", brandDto)).thenReturn(brandDto);

        mockMvc.perform(put("/api/vehicle_brands/{id}", "brand-1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brandDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("brand-1"));

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleBrandsService).updateBrand("brand-1", brandDto);
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void deleteRemovesBrandForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");

        mockMvc.perform(delete("/api/vehicle_brands/{id}", "brand-1"))
                .andExpect(status().isOk());

        verify(authenticationService).checkAuthentication(any());
        verify(vehicleBrandsService).deleteBrand("brand-1");
    }
}

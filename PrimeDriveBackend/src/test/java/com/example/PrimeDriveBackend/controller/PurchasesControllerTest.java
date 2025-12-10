package com.example.PrimeDriveBackend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.example.PrimeDriveBackend.dto.PurchasesDto;
import com.example.PrimeDriveBackend.filter.JwtFilter;
import com.example.PrimeDriveBackend.service.AuthenticationService;
import com.example.PrimeDriveBackend.service.PurchasesService;
import com.example.PrimeDriveBackend.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PurchasesController.class)
@AutoConfigureMockMvc(addFilters = false)
class PurchasesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PurchasesService purchasesService;

    @MockitoBean
    private AuthenticationService authenticationService;

    @MockitoBean
    private JwtFilter jwtFilter;

    @MockitoBean
    private JwtUtil jwtUtil;

    private PurchasesDto purchaseDto;

    @BeforeEach
    void setUp() {
        purchaseDto = new PurchasesDto("pur-1", "buyer-1", "seller-1", "veh-1");
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void listAllReturnsPurchases() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(purchasesService.getAllPurchases()).thenReturn(List.of(purchaseDto));

        mockMvc.perform(get("/api/purchases"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("pur-1"));

        verify(authenticationService).checkAuthentication(any());
        verify(purchasesService).getAllPurchases();
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void getByIdReturnsPurchase() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(purchasesService.getPurchaseById("pur-1")).thenReturn(purchaseDto);

        mockMvc.perform(get("/api/purchases/{id}", "pur-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("pur-1"));

        verify(authenticationService).checkAuthentication(any());
        verify(purchasesService).getPurchaseById("pur-1");
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void createPersistsPurchaseForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");
        when(purchasesService.savePurchase(any(PurchasesDto.class))).thenReturn(purchaseDto);

        mockMvc.perform(post("/api/purchases")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(purchaseDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("pur-1"));

        verify(authenticationService).checkAuthentication(any());
        verify(purchasesService).savePurchase(any(PurchasesDto.class));
    }

    @Test
    @WithMockUser(roles = { "ADMIN" })
    void deleteRemovesPurchaseForAdmin() throws Exception {
        when(authenticationService.checkAuthentication(any())).thenReturn("admin-1");

        mockMvc.perform(delete("/api/purchases/{id}", "pur-1"))
                .andExpect(status().isOk());

        verify(authenticationService).checkAuthentication(any());
        verify(purchasesService).deletePurchase("pur-1");
    }
}

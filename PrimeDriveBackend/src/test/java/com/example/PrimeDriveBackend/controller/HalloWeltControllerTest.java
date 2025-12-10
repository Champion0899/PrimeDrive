package com.example.PrimeDriveBackend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.PrimeDriveBackend.filter.JwtFilter;
import com.example.PrimeDriveBackend.util.JwtUtil;

@WebMvcTest(HalloWeltController.class)
@AutoConfigureMockMvc(addFilters = false)
class HalloWeltControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtFilter jwtFilter;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Test
    void halloReturnsGreeting() throws Exception {
        mockMvc.perform(get("/hallo"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hallo"));
    }
}

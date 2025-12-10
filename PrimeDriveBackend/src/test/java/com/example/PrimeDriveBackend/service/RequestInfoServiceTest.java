package com.example.PrimeDriveBackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
class RequestInfoServiceTest {

    private final RequestInfoService requestInfoService = new RequestInfoService();

    @Mock
    private HttpServletRequest request;

    @Test
    void returnsForwardedIpWhenHeaderPresent() {
        when(request.getHeader("X-Forwarded-For")).thenReturn("1.1.1.1");

        String ip = requestInfoService.getClientIp(request);

        assertEquals("1.1.1.1", ip);
    }

    @Test
    void returnsRemoteAddrWhenHeaderMissing() {
        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("2.2.2.2");

        String ip = requestInfoService.getClientIp(request);

        assertEquals("2.2.2.2", ip);
    }

    @Test
    void returnsUnknownOnException() {
        doThrow(new RuntimeException("fail")).when(request).getHeader("X-Forwarded-For");

        String ip = requestInfoService.getClientIp(request);

        assertEquals("UNKNOWN", ip);
    }
}

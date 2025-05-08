package com.example.PrimeDriveBackend.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;
import java.util.Optional;

import com.example.PrimeDriveBackend.model.PlattformNutzerkonto;
import com.example.PrimeDriveBackend.service.PlattformNutzerkontoService;
import com.example.PrimeDriveBackend.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final PlattformNutzerkontoService plattformNutzerkontoService;

    public JwtFilter(JwtUtil jwtUtil, PlattformNutzerkontoService plattformNutzerkontoService) {
        this.jwtUtil = jwtUtil;
        this.plattformNutzerkontoService = plattformNutzerkontoService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        Integer id = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            id = jwtUtil.extractUserId(jwt);
        }

        if (id != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<PlattformNutzerkonto> userDetails = plattformNutzerkontoService.findbyId(id);
            if (userDetails.isPresent() && jwtUtil.validateToken(jwt, userDetails.get())) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails.get(), null, userDetails.get().getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
    
}

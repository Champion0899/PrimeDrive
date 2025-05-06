package com.example.PrimeDriveBackend.controller;

import com.example.PrimeDriveBackend.Dto.FahrzeugDto;
import com.example.PrimeDriveBackend.service.FahrzeugService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/fahrzeuge")
@RequiredArgsConstructor
public class FahrzeugController {
    private final FahrzeugService fahrzeugService;

    @GetMapping
    public List<FahrzeugDto> listAll() {
        return fahrzeugService.getAllFahrzeuge();
    }

    @GetMapping("/{id}")
    public FahrzeugDto getById(@PathVariable Long id) {
        return fahrzeugService.getFahrzeugById(id);
    }

    @PostMapping
    public FahrzeugDto create(@RequestBody FahrzeugDto dto) {
        return fahrzeugService.saveFahrzeug(dto);
    }
}
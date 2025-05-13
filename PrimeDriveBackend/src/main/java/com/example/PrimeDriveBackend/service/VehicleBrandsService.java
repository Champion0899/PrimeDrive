package com.example.PrimeDriveBackend.service;

import java.util.List;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.repository.VehicleBrandsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleBrandsService {
    private final VehicleBrandsRepository vehicleBrandsRepository;
    private final VehicleBrandsMapper vehicleBrandsMapper;

    public List<VehicleBrandsDto> getAllBrands() {
        return vehicleBrandsRepository.findAll()
                .stream()
                .map(vehicleBrandsMapper::toDto)
                .toList();
    }

    public VehicleBrandsDto getBrandById(String id) {
        return vehicleBrandsRepository.findById(id)
                .map(vehicleBrandsMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
    }

    public VehicleBrandsDto saveBrand(VehicleBrandsDto dto) {
        VehicleBrands vehicleBrands = vehicleBrandsMapper.toEntity(dto);
        return vehicleBrandsMapper.toDto(vehicleBrandsRepository.save(vehicleBrands));
    }
}

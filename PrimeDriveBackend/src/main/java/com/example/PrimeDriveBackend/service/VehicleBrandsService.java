package com.example.PrimeDriveBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.Dto.VehicleBrandsDto;
import com.example.PrimeDriveBackend.Mapper.VehicleBrandsMapper;
import com.example.PrimeDriveBackend.model.VehicleBrands;
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

    public VehicleBrands getBrandByIdEntity(String id) {
        return vehicleBrandsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
    }

    public VehicleBrandsDto saveBrand(VehicleBrandsDto dto) {
        VehicleBrands vehicleBrands = vehicleBrandsMapper.toEntity(dto);
        return vehicleBrandsMapper.toDto(vehicleBrandsRepository.save(vehicleBrands));
    }

    public VehicleBrandsDto updateBrand(String id, VehicleBrandsDto dto) {
        VehicleBrands existing = vehicleBrandsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));

        VehicleBrands updatedVehicleBrand = vehicleBrandsMapper.toEntity(dto);
        updatedVehicleBrand.setId(existing.getId());

        return vehicleBrandsMapper.toDto(vehicleBrandsRepository.save(existing));
    }

    public void deleteBrand(String id) {
        if (!vehicleBrandsRepository.existsById(id)) {
            throw new RuntimeException("Brand not found with id: " + id);
        }
        vehicleBrandsRepository.deleteById(id);
    }
}

package com.example.PrimeDriveBackend.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.PrimeDriveBackend.repository.VehicleRepository;
import com.example.PrimeDriveBackend.model.Vehicle;

import com.example.PrimeDriveBackend.Dto.VehicleSpecsDto;
import com.example.PrimeDriveBackend.Mapper.VehicleSpecsMapper;
import com.example.PrimeDriveBackend.model.VehicleSpecs;
import com.example.PrimeDriveBackend.repository.VehicleSpecsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleSpecsService {
    private final VehicleSpecsRepository vehicleSpecsRepository;
    private final VehicleSpecsMapper vehicleSpecsMapper;
    private final VehicleRepository vehicleRepository;

    public List<VehicleSpecsDto> getAllSpecs() {
        return vehicleSpecsRepository.findAll()
                .stream()
                .map(vehicleSpecsMapper::toDto)
                .toList();
    }

    public VehicleSpecsDto getSpecsById(String id) {
        return vehicleSpecsRepository.findById(id)
                .map(vehicleSpecsMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Seats not found with id: " + id));
    }

    public VehicleSpecs getSpecsByIdEntity(String id) {
        return vehicleSpecsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seats not found with id: " + id));
    }

    public VehicleSpecsDto saveSpecs(VehicleSpecsDto dto) {
        VehicleSpecs vehicleSpecs = vehicleSpecsMapper.toEntity(dto);
        return vehicleSpecsMapper.toDto(vehicleSpecsRepository.save(vehicleSpecs));
    }

    public VehicleSpecsDto updateSpecs(String id, VehicleSpecsDto updatedDto) {
        VehicleSpecs existing = vehicleSpecsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specs not found with id: " + id));

        Vehicle vehicle = vehicleRepository.findBySpecs_Id(id)
                .orElseThrow(() -> new RuntimeException("No vehicle found for given specs ID"));

        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!vehicle.getUsers().equals(currentUserId)) {
            throw new SecurityException("You are not authorized to update these specs.");
        }

        VehicleSpecs updatedSpecs = vehicleSpecsMapper.toEntity(updatedDto);
        updatedSpecs.setId(existing.getId());

        return vehicleSpecsMapper.toDto(vehicleSpecsRepository.save(updatedSpecs));
    }

    public void deleteSpecs(String id) {
        if (!vehicleSpecsRepository.existsById(id)) {
            throw new RuntimeException("Specs not found with id: " + id);
        }

        Vehicle vehicle = vehicleRepository.findBySpecs_Id(id)
                .orElseThrow(() -> new RuntimeException("No vehicle found for given specs ID"));

        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!vehicle.getUsers().equals(currentUserId)) {
            throw new SecurityException("You are not authorized to delete these specs.");
        }

        vehicleSpecsRepository.deleteById(id);
    }
}

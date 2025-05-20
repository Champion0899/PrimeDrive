package com.example.PrimeDriveBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.Dto.VehicleDoorsDto;
import com.example.PrimeDriveBackend.Mapper.VehicleDoorsMapper;
import com.example.PrimeDriveBackend.model.VehicleDoors;
import com.example.PrimeDriveBackend.repository.VehicleDoorsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleDoorsService {

    private final VehicleDoorsRepository vehicleDoorsRepository;
    private final VehicleDoorsMapper vehicleDoorsMapper;

    public List<VehicleDoorsDto> getAllDoors() {
        return vehicleDoorsRepository.findAll()
                .stream()
                .map(vehicleDoorsMapper::toDto)
                .toList();
    }

    public VehicleDoorsDto getDoorsById(String id) {
        return vehicleDoorsRepository.findById(id)
                .map(vehicleDoorsMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Doors not found with id: " + id));
    }

    public VehicleDoors getDoorsByIdEntity(String id) {
        return vehicleDoorsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doors not found with id: " + id));
    }

    public VehicleDoorsDto saveDoors(VehicleDoorsDto dto) {
        VehicleDoors vehicleDoors = vehicleDoorsMapper.toEntity(dto);
        return vehicleDoorsMapper.toDto(vehicleDoorsRepository.save(vehicleDoors));
    }

    public VehicleDoorsDto updateDoors(String id, VehicleDoorsDto dto) {
        VehicleDoors existing = vehicleDoorsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doors not found with id: " + id));

        VehicleDoors updatedVehicleDoors = vehicleDoorsMapper.toEntity(dto);
        updatedVehicleDoors.setId(existing.getId());
        return vehicleDoorsMapper.toDto(vehicleDoorsRepository.save(existing));
    }

    public void deleteDoors(String id) {
        VehicleDoors existing = vehicleDoorsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doors not found with id: " + id));
        vehicleDoorsRepository.delete(existing);
    }
}

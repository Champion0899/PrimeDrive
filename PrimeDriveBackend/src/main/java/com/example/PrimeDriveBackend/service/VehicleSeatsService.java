package com.example.PrimeDriveBackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PrimeDriveBackend.Dto.VehicleSeatsDto;
import com.example.PrimeDriveBackend.Mapper.VehicleSeatsMapper;
import com.example.PrimeDriveBackend.model.VehicleSeats;
import com.example.PrimeDriveBackend.repository.VehicleSeatsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleSeatsService {
    private final VehicleSeatsRepository vehicleSeatsRepository;
    private final VehicleSeatsMapper vehicleSeatsMapper;

    public List<VehicleSeatsDto> getAllSeats() {
        return vehicleSeatsRepository.findAll()
                .stream()
                .map(vehicleSeatsMapper::toDto)
                .toList();
    }

    public VehicleSeatsDto getSeatsById(String id) {
        return vehicleSeatsRepository.findById(id)
                .map(vehicleSeatsMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Seats not found with id: " + id));
    }

    public VehicleSeats getSeatsByIdEntity(String id) {
        return vehicleSeatsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seats not found with id: " + id));
    }

    public VehicleSeatsDto saveSeats(VehicleSeatsDto dto) {
        VehicleSeats vehicleSeats = vehicleSeatsMapper.toEntity(dto);
        return vehicleSeatsMapper.toDto(vehicleSeatsRepository.save(vehicleSeats));
    }

    public VehicleSeatsDto updateSeats(String id, VehicleSeatsDto dto) {
        VehicleSeats existing = vehicleSeatsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seats not found with id: " + id));

        VehicleSeats updatedVehicleSeats = vehicleSeatsMapper.toEntity(dto);
        updatedVehicleSeats.setId(updatedVehicleSeats.getId());

        return vehicleSeatsMapper.toDto(vehicleSeatsRepository.save(existing));
    }

    public void deleteSeats(String id) {
        if (!vehicleSeatsRepository.existsById(id)) {
            throw new RuntimeException("Seats not found with id: " + id);
        }
        vehicleSeatsRepository.deleteById(id);
    }
}

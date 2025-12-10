package com.example.PrimeDriveBackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.PrimeDriveBackend.dto.VehicleFuelsDto;
import com.example.PrimeDriveBackend.mapper.VehicleFuelsMapper;
import com.example.PrimeDriveBackend.model.VehicleFuels;
import com.example.PrimeDriveBackend.repository.VehicleFuelsRepository;

@ExtendWith(MockitoExtension.class)
class VehicleFuelsServiceTest {

    @Mock
    private VehicleFuelsRepository vehicleFuelsRepository;

    @Mock
    private VehicleFuelsMapper vehicleFuelsMapper;

    @InjectMocks
    private VehicleFuelsService vehicleFuelsService;

    @Test
    void getFuelTypesReturnsDtos() {
        VehicleFuels fuel = new VehicleFuels();
        fuel.setId("fuel-1");
        VehicleFuelsDto dto = new VehicleFuelsDto();
        dto.setId("fuel-1");
        dto.setFuelType("Diesel");

        when(vehicleFuelsRepository.findAll()).thenReturn(List.of(fuel));
        when(vehicleFuelsMapper.toDto(fuel)).thenReturn(dto);

        List<VehicleFuelsDto> result = vehicleFuelsService.getFuelTypes();

        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
    }

    @Test
    void getFuelsByIdReturnsDto() {
        VehicleFuels fuel = new VehicleFuels();
        VehicleFuelsDto dto = new VehicleFuelsDto();
        dto.setFuelType("Diesel");

        when(vehicleFuelsRepository.findById("fuel-1")).thenReturn(Optional.of(fuel));
        when(vehicleFuelsMapper.toDto(fuel)).thenReturn(dto);

        assertEquals(dto, vehicleFuelsService.getFuelsById("fuel-1"));
    }

    @Test
    void getFuelsByIdThrowsWhenMissing() {
        when(vehicleFuelsRepository.findById("missing")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> vehicleFuelsService.getFuelsById("missing"));
    }

    @Test
    void saveFuelsPersists() {
        VehicleFuels fuel = new VehicleFuels();
        VehicleFuelsDto dto = new VehicleFuelsDto();
        dto.setFuelType("Diesel");

        when(vehicleFuelsMapper.toEntity(dto)).thenReturn(fuel);
        when(vehicleFuelsRepository.save(fuel)).thenReturn(fuel);
        when(vehicleFuelsMapper.toDto(fuel)).thenReturn(dto);

        VehicleFuelsDto result = vehicleFuelsService.saveFuels(dto);

        assertEquals(dto, result);
        verify(vehicleFuelsRepository).save(fuel);
    }

    @Test
    void updateFuelsPersists() {
        VehicleFuels existing = new VehicleFuels();
        VehicleFuelsDto dto = new VehicleFuelsDto();
        dto.setFuelType("Diesel");

        when(vehicleFuelsRepository.findById("fuel-1")).thenReturn(Optional.of(existing));
        when(vehicleFuelsMapper.toEntity(dto)).thenReturn(new VehicleFuels());
        when(vehicleFuelsRepository.save(existing)).thenReturn(existing);
        when(vehicleFuelsMapper.toDto(existing)).thenReturn(dto);

        VehicleFuelsDto result = vehicleFuelsService.updateFuels("fuel-1", dto);

        assertEquals(dto, result);
        verify(vehicleFuelsRepository).save(existing);
    }

    @Test
    void updateFuelsThrowsWhenMissing() {
        VehicleFuelsDto dto = new VehicleFuelsDto();
        dto.setFuelType("Diesel");
        when(vehicleFuelsRepository.findById("fuel-1")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> vehicleFuelsService.updateFuels("fuel-1", dto));
    }

    @Test
    void deleteFuelsDeletesWhenExists() {
        VehicleFuels existing = new VehicleFuels();
        when(vehicleFuelsRepository.findById("fuel-1")).thenReturn(Optional.of(existing));

        vehicleFuelsService.deleteFuels("fuel-1");

        verify(vehicleFuelsRepository).delete(existing);
    }

    @Test
    void deleteFuelsThrowsWhenMissing() {
        when(vehicleFuelsRepository.findById("fuel-1")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> vehicleFuelsService.deleteFuels("fuel-1"));
    }
}

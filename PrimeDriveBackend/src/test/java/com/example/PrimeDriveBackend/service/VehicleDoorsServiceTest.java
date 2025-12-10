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

import com.example.PrimeDriveBackend.dto.VehicleDoorsDto;
import com.example.PrimeDriveBackend.mapper.VehicleDoorsMapper;
import com.example.PrimeDriveBackend.model.VehicleDoors;
import com.example.PrimeDriveBackend.repository.VehicleDoorsRepository;

@ExtendWith(MockitoExtension.class)
class VehicleDoorsServiceTest {

    @Mock
    private VehicleDoorsRepository vehicleDoorsRepository;

    @Mock
    private VehicleDoorsMapper vehicleDoorsMapper;

    @InjectMocks
    private VehicleDoorsService vehicleDoorsService;

    @Test
    void getAllDoorsReturnsDtos() {
        VehicleDoors doors = new VehicleDoors();
        doors.setId("doors-1");
        VehicleDoorsDto dto = new VehicleDoorsDto();
        dto.setId("doors-1");

        when(vehicleDoorsRepository.findAll()).thenReturn(List.of(doors));
        when(vehicleDoorsMapper.toDto(doors)).thenReturn(dto);

        List<VehicleDoorsDto> result = vehicleDoorsService.getAllDoors();

        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
    }

    @Test
    void getDoorsByIdReturnsDto() {
        VehicleDoors doors = new VehicleDoors();
        VehicleDoorsDto dto = new VehicleDoorsDto();

        when(vehicleDoorsRepository.findById("doors-1")).thenReturn(Optional.of(doors));
        when(vehicleDoorsMapper.toDto(doors)).thenReturn(dto);

        assertEquals(dto, vehicleDoorsService.getDoorsById("doors-1"));
    }

    @Test
    void getDoorsByIdThrowsWhenMissing() {
        when(vehicleDoorsRepository.findById("missing")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> vehicleDoorsService.getDoorsById("missing"));
    }

    @Test
    void saveDoorsPersists() {
        VehicleDoors doors = new VehicleDoors();
        VehicleDoorsDto dto = new VehicleDoorsDto();

        when(vehicleDoorsMapper.toEntity(dto)).thenReturn(doors);
        when(vehicleDoorsRepository.save(doors)).thenReturn(doors);
        when(vehicleDoorsMapper.toDto(doors)).thenReturn(dto);

        VehicleDoorsDto result = vehicleDoorsService.saveDoors(dto);

        assertEquals(dto, result);
        verify(vehicleDoorsRepository).save(doors);
    }

    @Test
    void updateDoorsPersists() {
        VehicleDoors existing = new VehicleDoors();
        VehicleDoorsDto dto = new VehicleDoorsDto();

        when(vehicleDoorsRepository.findById("doors-1")).thenReturn(Optional.of(existing));
        when(vehicleDoorsMapper.toEntity(dto)).thenReturn(new VehicleDoors());
        when(vehicleDoorsRepository.save(existing)).thenReturn(existing);
        when(vehicleDoorsMapper.toDto(existing)).thenReturn(dto);

        VehicleDoorsDto result = vehicleDoorsService.updateDoors("doors-1", dto);

        assertEquals(dto, result);
        verify(vehicleDoorsRepository).save(existing);
    }

    @Test
    void updateDoorsThrowsWhenMissing() {
        VehicleDoorsDto dto = new VehicleDoorsDto();
        when(vehicleDoorsRepository.findById("doors-1")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> vehicleDoorsService.updateDoors("doors-1", dto));
    }

    @Test
    void deleteDoorsDeletesWhenExists() {
        VehicleDoors existing = new VehicleDoors();
        when(vehicleDoorsRepository.findById("doors-1")).thenReturn(Optional.of(existing));

        vehicleDoorsService.deleteDoors("doors-1");

        verify(vehicleDoorsRepository).delete(existing);
    }

    @Test
    void deleteDoorsThrowsWhenMissing() {
        when(vehicleDoorsRepository.findById("doors-1")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> vehicleDoorsService.deleteDoors("doors-1"));
    }
}

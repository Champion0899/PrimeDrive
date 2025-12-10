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

import com.example.PrimeDriveBackend.dto.VehicleSeatsDto;
import com.example.PrimeDriveBackend.mapper.VehicleSeatsMapper;
import com.example.PrimeDriveBackend.model.VehicleSeats;
import com.example.PrimeDriveBackend.repository.VehicleSeatsRepository;

@ExtendWith(MockitoExtension.class)
class VehicleSeatsServiceTest {

    @Mock
    private VehicleSeatsRepository vehicleSeatsRepository;

    @Mock
    private VehicleSeatsMapper vehicleSeatsMapper;

    @InjectMocks
    private VehicleSeatsService vehicleSeatsService;

    @Test
    void getAllSeatsReturnsDtos() {
        VehicleSeats seats = new VehicleSeats();
        seats.setId("seats-1");
        VehicleSeatsDto dto = new VehicleSeatsDto();
        dto.setId("seats-1");

        when(vehicleSeatsRepository.findAll()).thenReturn(List.of(seats));
        when(vehicleSeatsMapper.toDto(seats)).thenReturn(dto);

        List<VehicleSeatsDto> result = vehicleSeatsService.getAllSeats();

        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
    }

    @Test
    void getSeatsByIdReturnsDto() {
        VehicleSeats seats = new VehicleSeats();
        VehicleSeatsDto dto = new VehicleSeatsDto();

        when(vehicleSeatsRepository.findById("seats-1")).thenReturn(Optional.of(seats));
        when(vehicleSeatsMapper.toDto(seats)).thenReturn(dto);

        assertEquals(dto, vehicleSeatsService.getSeatsById("seats-1"));
    }

    @Test
    void getSeatsByIdThrowsWhenMissing() {
        when(vehicleSeatsRepository.findById("missing")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> vehicleSeatsService.getSeatsById("missing"));
    }

    @Test
    void saveSeatsPersists() {
        VehicleSeats seats = new VehicleSeats();
        VehicleSeatsDto dto = new VehicleSeatsDto();

        when(vehicleSeatsMapper.toEntity(dto)).thenReturn(seats);
        when(vehicleSeatsRepository.save(seats)).thenReturn(seats);
        when(vehicleSeatsMapper.toDto(seats)).thenReturn(dto);

        VehicleSeatsDto result = vehicleSeatsService.saveSeats(dto);

        assertEquals(dto, result);
        verify(vehicleSeatsRepository).save(seats);
    }

    @Test
    void updateSeatsPersists() {
        VehicleSeats existing = new VehicleSeats();
        VehicleSeatsDto dto = new VehicleSeatsDto();

        when(vehicleSeatsRepository.findById("seats-1")).thenReturn(Optional.of(existing));
        when(vehicleSeatsMapper.toEntity(dto)).thenReturn(new VehicleSeats());
        when(vehicleSeatsRepository.save(existing)).thenReturn(existing);
        when(vehicleSeatsMapper.toDto(existing)).thenReturn(dto);

        VehicleSeatsDto result = vehicleSeatsService.updateSeats("seats-1", dto);

        assertEquals(dto, result);
        verify(vehicleSeatsRepository).save(existing);
    }

    @Test
    void updateSeatsThrowsWhenMissing() {
        VehicleSeatsDto dto = new VehicleSeatsDto();
        when(vehicleSeatsRepository.findById("seats-1")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> vehicleSeatsService.updateSeats("seats-1", dto));
    }

    @Test
    void deleteSeatsDeletesWhenExists() {
        when(vehicleSeatsRepository.existsById("seats-1")).thenReturn(true);

        vehicleSeatsService.deleteSeats("seats-1");

        verify(vehicleSeatsRepository).deleteById("seats-1");
    }

    @Test
    void deleteSeatsThrowsWhenMissing() {
        when(vehicleSeatsRepository.existsById("seats-1")).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> vehicleSeatsService.deleteSeats("seats-1"));
    }
}

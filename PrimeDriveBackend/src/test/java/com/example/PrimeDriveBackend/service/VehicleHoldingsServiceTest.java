package com.example.PrimeDriveBackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.PrimeDriveBackend.dto.VehicleHoldingsDto;
import com.example.PrimeDriveBackend.exception.EntityInUseException;
import com.example.PrimeDriveBackend.mapper.VehicleHoldingsMapper;
import com.example.PrimeDriveBackend.model.VehicleHoldings;
import com.example.PrimeDriveBackend.repository.VehicleHoldingsRepository;
import com.example.PrimeDriveBackend.util.ImageValidator;

@ExtendWith(MockitoExtension.class)
class VehicleHoldingsServiceTest {

    @Mock
    private VehicleHoldingsRepository vehicleHoldingsRepository;

    @Mock
    private VehicleHoldingsMapper vehicleHoldingsMapper;

    @InjectMocks
    private VehicleHoldingsService vehicleHoldingsService;

    private VehicleHoldings holding;
    private VehicleHoldingsDto holdingDto;

    @BeforeEach
    void setUp() {
        holding = new VehicleHoldings();
        holding.setId("holding-1");
        holding.setName("VW");
        holding.setLogo("logo.png");
        holding.setFounding(2020);

        holdingDto = new VehicleHoldingsDto();
        holdingDto.setId("holding-1");
        holdingDto.setName("VW");
        holdingDto.setLogo("logo.png");
        holdingDto.setFounding(2020);
    }

    @Test
    void getAllHoldingsReturnsDtos() {
        when(vehicleHoldingsRepository.findAll()).thenReturn(List.of(holding));
        when(vehicleHoldingsMapper.toDto(holding)).thenReturn(holdingDto);

        List<VehicleHoldingsDto> result = vehicleHoldingsService.getAllHoldings();

        assertEquals(1, result.size());
        assertEquals(holdingDto, result.get(0));
    }

    @Test
    void getHoldingByIdReturnsDto() {
        when(vehicleHoldingsRepository.findById("holding-1")).thenReturn(Optional.of(holding));
        when(vehicleHoldingsMapper.toDto(holding)).thenReturn(holdingDto);

        VehicleHoldingsDto result = vehicleHoldingsService.getHoldingById("holding-1");

        assertEquals(holdingDto, result);
    }

    @Test
    void getHoldingByIdThrowsWhenMissing() {
        when(vehicleHoldingsRepository.findById("missing")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> vehicleHoldingsService.getHoldingById("missing"));
    }

    @Test
    void saveHoldingValidatesAndPersists() {
        VehicleHoldings mapped = new VehicleHoldings();
        mapped.setId("holding-1");

        try (MockedStatic<ImageValidator> mocked = org.mockito.Mockito.mockStatic(ImageValidator.class)) {
            when(vehicleHoldingsMapper.toEntity(holdingDto)).thenReturn(mapped);
            when(vehicleHoldingsRepository.save(mapped)).thenReturn(mapped);
            when(vehicleHoldingsMapper.toDto(mapped)).thenReturn(holdingDto);

            VehicleHoldingsDto result = vehicleHoldingsService.saveHolding(holdingDto);

            assertEquals(holdingDto, result);
            mocked.verify(() -> ImageValidator.validate("logo.png"));
            verify(vehicleHoldingsRepository).save(mapped);
        }
    }

    @Test
    void updateHoldingValidatesAndPersists() {
        VehicleHoldingsDto updateDto = new VehicleHoldingsDto();
        updateDto.setId("holding-1");
        updateDto.setName("Audi");
        updateDto.setLogo("logo.png");
        updateDto.setFounding(2021);

        VehicleHoldings mapped = new VehicleHoldings();
        mapped.setId("holding-1");

        try (MockedStatic<ImageValidator> mocked = org.mockito.Mockito.mockStatic(ImageValidator.class)) {
            when(vehicleHoldingsRepository.findById("holding-1")).thenReturn(Optional.of(holding));
            when(vehicleHoldingsMapper.toEntity(updateDto)).thenReturn(mapped);
            when(vehicleHoldingsRepository.save(mapped)).thenReturn(mapped);
            when(vehicleHoldingsMapper.toDto(mapped)).thenReturn(updateDto);

            VehicleHoldingsDto result = vehicleHoldingsService.updateHolding("holding-1", updateDto);

            assertEquals(updateDto, result);
            mocked.verify(() -> ImageValidator.validate("logo.png"));
            verify(vehicleHoldingsRepository).save(mapped);
        }
    }

    @Test
    void deleteHoldingThrowsWhenNotFound() {
        when(vehicleHoldingsRepository.existsById("missing")).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> vehicleHoldingsService.deleteHolding("missing"));
    }

    @Test
    void deleteHoldingThrowsWhenInUse() {
        when(vehicleHoldingsRepository.existsById("holding-1")).thenReturn(true);
        when(vehicleHoldingsRepository.isHoldingInUse("holding-1")).thenReturn(true);

        assertThrows(EntityInUseException.class, () -> vehicleHoldingsService.deleteHolding("holding-1"));
    }

    @Test
    void deleteHoldingDeletesWhenFree() {
        when(vehicleHoldingsRepository.existsById("holding-1")).thenReturn(true);
        when(vehicleHoldingsRepository.isHoldingInUse("holding-1")).thenReturn(false);

        vehicleHoldingsService.deleteHolding("holding-1");

        verify(vehicleHoldingsRepository).deleteById("holding-1");
    }
}

package com.example.PrimeDriveBackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
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

import com.example.PrimeDriveBackend.dto.VehicleBrandsDto;
import com.example.PrimeDriveBackend.exception.EntityInUseException;
import com.example.PrimeDriveBackend.mapper.VehicleBrandsMapper;
import com.example.PrimeDriveBackend.model.VehicleBrands;
import com.example.PrimeDriveBackend.model.VehicleHoldings;
import com.example.PrimeDriveBackend.repository.VehicleBrandsRepository;
import com.example.PrimeDriveBackend.util.ImageValidator;

@ExtendWith(MockitoExtension.class)
class VehicleBrandsServiceTest {

    @Mock
    private VehicleBrandsRepository vehicleBrandsRepository;

    @Mock
    private VehicleBrandsMapper vehicleBrandsMapper;

    @Mock
    private VehicleHoldingsService vehicleHoldingsService;

    @InjectMocks
    private VehicleBrandsService vehicleBrandsService;

    private VehicleBrands brand;
    private VehicleBrandsDto brandDto;
    private VehicleHoldings holding;

    @BeforeEach
    void setUp() {
        holding = new VehicleHoldings();
        holding.setId("holding-1");

        brand = new VehicleBrands();
        brand.setId("brand-1");
        brand.setName("Brand");
        brand.setLogo("logo.png");
        brand.setHolding(holding);

        brandDto = new VehicleBrandsDto();
        brandDto.setId("brand-1");
        brandDto.setName("Brand");
        brandDto.setLogo("logo.png");
        brandDto.setFounding(2020);
        brandDto.setHoldingId("holding-1");
    }

    @Test
    void getAllBrandsReturnsDtos() {
        when(vehicleBrandsRepository.findAll()).thenReturn(List.of(brand));
        when(vehicleBrandsMapper.toDto(brand)).thenReturn(brandDto);

        List<VehicleBrandsDto> result = vehicleBrandsService.getAllBrands();

        assertEquals(1, result.size());
        assertEquals(brandDto, result.get(0));
        verify(vehicleBrandsRepository).findAll();
    }

    @Test
    void getBrandByIdReturnsDto() {
        when(vehicleBrandsRepository.findById("brand-1")).thenReturn(Optional.of(brand));
        when(vehicleBrandsMapper.toDto(brand)).thenReturn(brandDto);

        VehicleBrandsDto result = vehicleBrandsService.getBrandById("brand-1");

        assertEquals(brandDto, result);
    }

    @Test
    void getBrandByIdThrowsWhenMissing() {
        when(vehicleBrandsRepository.findById("missing")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> vehicleBrandsService.getBrandById("missing"));
    }

    @Test
    void saveBrandValidatesAndPersists() {
        VehicleBrands mapped = new VehicleBrands();
        mapped.setId("brand-1");

        try (MockedStatic<ImageValidator> mocked = org.mockito.Mockito.mockStatic(ImageValidator.class)) {
            when(vehicleBrandsMapper.toEntity(brandDto)).thenReturn(mapped);
            when(vehicleBrandsRepository.save(mapped)).thenReturn(mapped);
            when(vehicleBrandsMapper.toDto(mapped)).thenReturn(brandDto);

            VehicleBrandsDto result = vehicleBrandsService.saveBrand(brandDto);

            assertEquals(brandDto, result);
            mocked.verify(() -> ImageValidator.validate("logo.png"));
            verify(vehicleBrandsRepository).save(mapped);
        }
    }

    @Test
    void updateBrandChangesHoldingWhenDifferent() {
        VehicleHoldings newHolding = new VehicleHoldings();
        newHolding.setId("holding-2");
        VehicleBrandsDto updateDto = new VehicleBrandsDto();
        updateDto.setId("brand-1");
        updateDto.setName("Brand");
        updateDto.setLogo("logo.png");
        updateDto.setFounding(2021);
        updateDto.setHoldingId("holding-2");

        VehicleBrands mapped = new VehicleBrands();
        mapped.setId("brand-1");

        try (MockedStatic<ImageValidator> mocked = org.mockito.Mockito.mockStatic(ImageValidator.class)) {
            when(vehicleBrandsRepository.findById("brand-1")).thenReturn(Optional.of(brand));
            when(vehicleHoldingsService.getVehicleHoldingEntityById("holding-2")).thenReturn(newHolding);
            when(vehicleBrandsMapper.toEntity(updateDto)).thenReturn(mapped);
            when(vehicleBrandsRepository.save(brand)).thenReturn(brand);
            when(vehicleBrandsMapper.toDto(brand)).thenReturn(updateDto);

            VehicleBrandsDto result = vehicleBrandsService.updateBrand("brand-1", updateDto);

            assertEquals(updateDto, result);
            assertSame(newHolding, brand.getHolding());
            mocked.verify(() -> ImageValidator.validate("logo.png"));
            verify(vehicleHoldingsService).getVehicleHoldingEntityById("holding-2");
            verify(vehicleBrandsMapper).toEntity(updateDto);
            verify(vehicleBrandsRepository).save(brand);
        }
    }

    @Test
    void updateBrandSkipsHoldingLookupWhenUnchanged() {
        try (MockedStatic<ImageValidator> mocked = org.mockito.Mockito.mockStatic(ImageValidator.class)) {
            when(vehicleBrandsRepository.findById("brand-1")).thenReturn(Optional.of(brand));
            when(vehicleBrandsMapper.toEntity(brandDto)).thenReturn(brand);
            when(vehicleBrandsRepository.save(brand)).thenReturn(brand);
            when(vehicleBrandsMapper.toDto(brand)).thenReturn(brandDto);

            vehicleBrandsService.updateBrand("brand-1", brandDto);

            verify(vehicleHoldingsService, never()).getVehicleHoldingEntityById(org.mockito.ArgumentMatchers.any());
            mocked.verify(() -> ImageValidator.validate("logo.png"));
        }
    }

    @Test
    void deleteBrandThrowsWhenNotFound() {
        when(vehicleBrandsRepository.existsById("missing")).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> vehicleBrandsService.deleteBrand("missing"));
    }

    @Test
    void deleteBrandThrowsWhenInUse() {
        when(vehicleBrandsRepository.existsById("brand-1")).thenReturn(true);
        when(vehicleBrandsRepository.isBrandInUse("brand-1")).thenReturn(true);

        assertThrows(EntityInUseException.class, () -> vehicleBrandsService.deleteBrand("brand-1"));
    }

    @Test
    void deleteBrandDeletesWhenFree() {
        when(vehicleBrandsRepository.existsById("brand-1")).thenReturn(true);
        when(vehicleBrandsRepository.isBrandInUse("brand-1")).thenReturn(false);

        vehicleBrandsService.deleteBrand("brand-1");

        verify(vehicleBrandsRepository).deleteById("brand-1");
    }
}

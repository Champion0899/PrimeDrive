package com.example.PrimeDriveBackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
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
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.PrimeDriveBackend.dto.PurchasesDto;
import com.example.PrimeDriveBackend.mapper.PurchasesMapper;
import com.example.PrimeDriveBackend.model.Purchases;
import com.example.PrimeDriveBackend.model.Users;
import com.example.PrimeDriveBackend.model.Vehicle;
import com.example.PrimeDriveBackend.repository.PurchasesRepository;

@ExtendWith(MockitoExtension.class)
class PurchasesServiceTest {

    @Mock
    private PurchasesRepository purchasesRepository;

    @Mock
    private PurchasesMapper purchasesMapper;

    @Mock
    private UserService userService;

    @Mock
    private VehicleService vehicleService;

    @InjectMocks
    private PurchasesService purchasesService;

    private Purchases purchase;
    private PurchasesDto purchaseDto;
    private Users buyer;
    private Users seller;
    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        buyer = new Users();
        buyer.setId("buyer-1");
        seller = new Users();
        seller.setId("seller-1");
        vehicle = new Vehicle();
        vehicle.setId("veh-1");

        purchase = new Purchases();
        purchase.setId("pur-1");

        purchaseDto = new PurchasesDto("pur-1", "buyer-1", "seller-1", "veh-1");
    }

    @Test
    void getAllPurchasesReturnsDtos() {
        when(purchasesRepository.findAll()).thenReturn(List.of(purchase));
        when(purchasesMapper.toDto(purchase)).thenReturn(purchaseDto);

        List<PurchasesDto> result = purchasesService.getAllPurchases();

        assertEquals(1, result.size());
        assertEquals(purchaseDto, result.get(0));
    }

    @Test
    void getPurchaseByIdReturnsDto() {
        when(purchasesRepository.findById("pur-1")).thenReturn(Optional.of(purchase));
        when(purchasesMapper.toDto(purchase)).thenReturn(purchaseDto);

        PurchasesDto result = purchasesService.getPurchaseById("pur-1");

        assertEquals(purchaseDto, result);
    }

    @Test
    void getPurchaseByIdThrowsWhenMissing() {
        when(purchasesRepository.findById("missing")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> purchasesService.getPurchaseById("missing"));
    }

    @Test
    void savePurchaseResolvesRelationsAndPersists() {
        when(purchasesMapper.toEntity(purchaseDto)).thenReturn(purchase);
        when(userService.getByIdEntity("buyer-1")).thenReturn(buyer);
        when(userService.getByIdEntity("seller-1")).thenReturn(seller);
        when(vehicleService.getVehicleByIdEntity("veh-1")).thenReturn(vehicle);
        when(purchasesRepository.save(purchase)).thenReturn(purchase);
        when(purchasesMapper.toDto(purchase)).thenReturn(purchaseDto);

        PurchasesDto result = purchasesService.savePurchase(purchaseDto);

        assertEquals(purchaseDto, result);
        assertSame(buyer, purchase.getBuyer());
        assertSame(seller, purchase.getSeller());
        assertSame(vehicle, purchase.getVehicle());
        verify(purchasesRepository).save(purchase);
    }

    @Test
    void deletePurchaseThrowsWhenNotFound() {
        when(purchasesRepository.existsById("missing")).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> purchasesService.deletePurchase("missing"));
    }

    @Test
    void deletePurchaseDeletesWhenExists() {
        when(purchasesRepository.existsById("pur-1")).thenReturn(true);

        purchasesService.deletePurchase("pur-1");

        verify(purchasesRepository).deleteById("pur-1");
    }
}

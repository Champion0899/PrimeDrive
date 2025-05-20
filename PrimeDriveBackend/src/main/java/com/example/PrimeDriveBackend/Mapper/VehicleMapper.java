package com.example.PrimeDriveBackend.Mapper;

import com.example.PrimeDriveBackend.Dto.VehicleDto;
import com.example.PrimeDriveBackend.model.Users;
import com.example.PrimeDriveBackend.model.Vehicle;
import com.example.PrimeDriveBackend.model.VehicleBrands;
import com.example.PrimeDriveBackend.model.VehicleColors;
import com.example.PrimeDriveBackend.model.VehicleSpecs;
import com.example.PrimeDriveBackend.model.VehicleTypes;
import com.example.PrimeDriveBackend.service.*;

import org.bouncycastle.crypto.RuntimeCryptoException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class VehicleMapper {

    private final UserService userService;
    private final VehicleBrandsService vehicleBrandsService;
    private final VehicleSpecsService vehicleSpecsService;
    private final VehicleTypesService vehicleTypesService;
    private final VehicleColorsService vehicleColorsService;

    VehicleMapper(UserService userService,
            VehicleSpecsService vehicleSpecsService,
            VehicleTypesService vehicleTypesService,
            VehicleColorsService vehicleColorsService,
            VehicleBrandsService vehicleBrandsService) {
        this.userService = userService;
        this.vehicleBrandsService = vehicleBrandsService;
        this.vehicleTypesService = vehicleTypesService;
        this.vehicleSpecsService = vehicleSpecsService;
        this.vehicleColorsService = vehicleColorsService;
    }

    public VehicleDto toDto(Vehicle vehicle) {
        VehicleDto dto = new VehicleDto();
        dto.setId(vehicle.getId());
        dto.setName(vehicle.getName());
        dto.setPrice(vehicle.getPrice());
        dto.setYear(vehicle.getYear());
        dto.setImage(vehicle.getImage());
        dto.setMileage(vehicle.getMileage());
        dto.setCondition(vehicle.getCondition());
        dto.setVehicleHistory(vehicle.getVehicleHistory());
        dto.setBrandsId(vehicle.getBrands().getId());
        dto.setSpecsId(vehicle.getSpecs().getId());
        dto.setTypesId(vehicle.getTypes().getId());
        dto.setColorsId(vehicle.getColors().getId());
        dto.setSellerId(vehicle.getUsers().getId());
        return dto;
    }

    public Vehicle toEntity(VehicleDto dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(dto.getId());
        vehicle.setName(dto.getName());
        vehicle.setPrice(dto.getPrice());
        vehicle.setYear(dto.getYear());
        vehicle.setImage(dto.getImage());
        vehicle.setMileage(dto.getMileage());
        vehicle.setCondition(dto.getCondition());
        vehicle.setVehicleHistory(dto.getVehicleHistory());

        VehicleBrands vehicleBrands = vehicleBrandsService.getBrandByIdEntity(dto.getBrandsId());
        if (vehicleBrands == null) {
            throw new RuntimeException("Brand not found");
        }
        vehicle.setBrands(vehicleBrands);

        VehicleTypes vehicleTypes = vehicleTypesService.getTypeByIdEntity(dto.getBrandsId());
        if (vehicleTypes == null) {
            throw new RuntimeException("Type not found");
        }
        vehicle.setTypes(vehicleTypes);

        VehicleSpecs vehicleSpecs = vehicleSpecsService.getSpecsByIdEntity(dto.getSpecsId());
        if (vehicleSpecs == null) {
            throw new RuntimeException("Specs not found");
        }
        vehicle.setSpecs(vehicleSpecs);

        VehicleColors vehicleColors = vehicleColorsService.getColorByIdEntity(dto.getColorsId());
        if (vehicleColors == null) {
            throw new RuntimeException("Color not found");
        }

        Users users = userService.getByIdEntity(dto.getSellerId());
        if (users == null) {
            throw new RuntimeException("User not found");
        }
        vehicle.setUsers(users);

        return vehicle;
    }
}
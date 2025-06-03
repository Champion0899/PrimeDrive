package com.example.PrimeDriveBackend.Mapper;

import org.bouncycastle.crypto.RuntimeCryptoException;
import org.springframework.stereotype.Component;

import com.example.PrimeDriveBackend.Dto.VehicleSpecsDto;
import com.example.PrimeDriveBackend.model.VehicleDoors;
import com.example.PrimeDriveBackend.model.VehicleEngine;
import com.example.PrimeDriveBackend.model.VehicleFuels;
import com.example.PrimeDriveBackend.model.VehicleSeats;
import com.example.PrimeDriveBackend.model.VehicleSpecs;
import com.example.PrimeDriveBackend.service.VehicleDoorsService;
import com.example.PrimeDriveBackend.service.VehicleEngineService;
import com.example.PrimeDriveBackend.service.VehicleFuelsService;
import com.example.PrimeDriveBackend.service.VehicleSeatsService;

/**
 * Mapper class for converting between VehicleSpecs entities and their DTO representations.
 *
 * This mapper handles the transformation of complex vehicle specification objects to DTOs and back.
 * It resolves related entities like doors, seats, engine, and fuels using their respective services.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0
 * Date: 2025-06-03
 */
@Component
public class VehicleSpecsMapper {

        private final VehicleDoorsService vehicleDoorsService;
        private final VehicleSeatsService vehicleSeatsService;
        private final VehicleEngineService vehicleEngineService;
        private final VehicleFuelsService vehicleFuelsService;

        public VehicleSpecsMapper(
                        VehicleDoorsService vehicleDoorsService,
                        VehicleSeatsService vehicleSeatsService,
                        VehicleEngineService vehicleEngineService,
                        VehicleFuelsService vehicleFuelService) {
                this.vehicleDoorsService = vehicleDoorsService;
                this.vehicleSeatsService = vehicleSeatsService;
                this.vehicleEngineService = vehicleEngineService;
                this.vehicleFuelsService = vehicleFuelService;
        }

        /**
         * Converts a VehicleSpecs entity to a VehicleSpecsDto.
         *
         * @param vehicleSpecs The VehicleSpecs entity containing detailed specifications.
         * @return A DTO representing the vehicle specifications.
         */
        public VehicleSpecsDto toDto(VehicleSpecs vehicleSpecs) {
                VehicleSpecsDto dto = new VehicleSpecsDto();
                dto.setId(vehicleSpecs.getId());
                dto.setPowerKw(vehicleSpecs.getPowerKw());
                dto.setPowerPs(vehicleSpecs.getPowerPs());
                dto.setLengthMillimeter(vehicleSpecs.getLengthMillimeter());
                dto.setWidthMillimeter(vehicleSpecs.getWidthMillimeter());
                dto.setHeightMillimeter(vehicleSpecs.getHeightMillimeter());
                dto.setTrunkInLiterMin(vehicleSpecs.getTrunkInLiterMin());
                dto.setTrunkInLiterMax(vehicleSpecs.getTrunkInLiterMax());
                dto.setZeroToHundredInSeconds(vehicleSpecs.getZeroToHundredInSeconds());
                dto.setTopSpeedInKmh(vehicleSpecs.getTopSpeedInKmh());
                dto.setConsumptionHundredInX(vehicleSpecs.getConsumptionHundredInX());
                dto.setCoTwoEmissionInGPerKm(vehicleSpecs.getCoTwoEmissionInGPerKm());
                dto.setCubicCapacity(vehicleSpecs.getCubicCapacity());
                dto.setDoorsId(vehicleSpecs.getDoors().getId());
                dto.setEngineId(vehicleSpecs.getEngine().getId());
                dto.setFuelsId(vehicleSpecs.getFuels().getId());
                dto.setSeatsId(vehicleSpecs.getSeats().getId());

                return dto;
        }

        /**
         * Converts a VehicleSpecsDto to a VehicleSpecs entity.
         *
         * Resolves related entities using VehicleDoorsService, VehicleSeatsService,
         * VehicleEngineService, and VehicleFuelsService.
         *
         * @param dto The DTO containing vehicle specification data.
         * @return A VehicleSpecs entity with resolved references.
         * @throws RuntimeException if any related entity is not found.
         */
        public VehicleSpecs toEntity(VehicleSpecsDto dto) {
                VehicleSpecs vehicleSpecs = new VehicleSpecs();
                vehicleSpecs.setId(dto.getId());
                vehicleSpecs.setPowerKw(dto.getPowerKw());
                vehicleSpecs.setPowerPs(dto.getPowerPs());
                vehicleSpecs.setLengthMillimeter(dto.getLengthMillimeter());
                vehicleSpecs.setWidthMillimeter(dto.getWidthMillimeter());
                vehicleSpecs.setHeightMillimeter(dto.getHeightMillimeter());
                vehicleSpecs.setTrunkInLiterMin(dto.getTrunkInLiterMin());
                vehicleSpecs.setTrunkInLiterMax(dto.getTrunkInLiterMax());
                vehicleSpecs.setZeroToHundredInSeconds(dto.getZeroToHundredInSeconds());
                vehicleSpecs.setTopSpeedInKmh(dto.getTopSpeedInKmh());
                vehicleSpecs.setConsumptionHundredInX(dto.getConsumptionHundredInX());
                vehicleSpecs.setCoTwoEmissionInGPerKm(dto.getCoTwoEmissionInGPerKm());
                vehicleSpecs.setCubicCapacity(dto.getCubicCapacity());

                VehicleDoors vehicleDoors = vehicleDoorsService.getDoorsByIdEntity(dto.getDoorsId());
                if (vehicleDoors == null) {
                        throw new RuntimeException("Doors not found");
                }
                vehicleSpecs.setDoors(vehicleDoors);

                VehicleSeats vehicleSeats = vehicleSeatsService.getSeatsByIdEntity(dto.getSeatsId());
                if (vehicleSeats == null) {
                        throw new RuntimeException("Seats not found");
                }
                vehicleSpecs.setSeats(vehicleSeats);

                VehicleEngine vehicleEngine = vehicleEngineService.getEngineByIdEntity(dto.getEngineId());
                if (vehicleEngine == null) {
                        throw new RuntimeException("Engine not found");
                }
                vehicleSpecs.setEngine(vehicleEngine);

                VehicleFuels vehicleFuels = vehicleFuelsService.getFuelsByIdEntity(dto.getFuelsId());
                if (vehicleFuels == null) {
                        throw new RuntimeCryptoException("Fuels not found");
                }
                vehicleSpecs.setFuels(vehicleFuels);

                return vehicleSpecs;
        }
}

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

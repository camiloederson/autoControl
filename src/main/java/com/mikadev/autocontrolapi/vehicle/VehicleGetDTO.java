package com.mikadev.autocontrolapi.vehicle;

import com.mikadev.autocontrolapi.vehicleBrand.VehicleBrandEntity;

public record VehicleGetDTO(
        Long id,
        String model,
        String color,
        Integer year,
        String licensePlate,
        VehicleBrandNameDTO vehicleBrand
) {
}

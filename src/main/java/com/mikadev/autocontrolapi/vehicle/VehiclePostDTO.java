package com.mikadev.autocontrolapi.vehicle;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record VehiclePostDTO (
        @NotBlank(message = "Model is required")
        String model,

        @NotBlank(message = "Color is required")
        String color,

        @NotNull(message = "Year is required")
        @Min(value = 1950)
        @Max(value = 2025)
        Integer year,

        @NotBlank(message = "LicensePlate is required")
        String licensePlate,

        @NotNull(message = "VehicleBrand id is required")
        Long vehicleBrandId,

        @NotNull(message = "CreatedBy id is required")
        Long createdByUserId
) {
}

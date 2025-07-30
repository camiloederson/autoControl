package com.mikadev.autocontrolapi.vehicleStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VehicleStatusPutDTO(
        @NotBlank(message = "Status is required")
        @Size(max = 50, message = "Status must not exceed 50 characters")
        String status,

        @Size(max = 255, message = "Description must not exceed 255 characters")
        String description,

        @NotNull(message = "UpdatedBy is required")
        Long updatedBy
) {
}

package com.mikadev.autocontrolapi.vehicleStatus;

import java.time.LocalDateTime;

public record VehicleStatusGetDTO(
        Long id,
        String status,
        String description,
        Long createdBy,
        Long updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

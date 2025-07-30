package com.mikadev.autocontrolapi.repairOrder;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RepairOrderPostDTO(
        @NotNull(message = "Customer ID is required")
        Long customerId,

        @NotNull(message = "Vehicle ID is required")
        Long vehicleId,

        @NotNull(message = "Mechanic ID is required")
        Long mechanicId,

        @DecimalMin(value = "0.0", inclusive = false, message = "Estimated value must be positive")
        BigDecimal estimatedValue,

        @DecimalMin(value = "0.0", inclusive = false, message = "Final value must be positive")
        BigDecimal finalValue,

        @Size(max = 2000, message = "Diagnosis must not exceed 2000 characters")
        String diagnosis,

        @Size(max = 2000, message = "Reported issue must not exceed 2000 characters")
        String reportedIssue,

        @Size(max = 2000, message = "Work description must not exceed 2000 characters")
        String workDescription,

        @Size(max = 2000, message = "Observations must not exceed 2000 characters")
        String observations,

        @NotNull(message = "Status ID is required")
        Long statusId,

        LocalDateTime entryDate,

        LocalDateTime exitDate,

        Boolean isClosed,

        Boolean invoiceGenerated,

        @NotNull(message = "Created by is required")
        Long createdBy
) {
}

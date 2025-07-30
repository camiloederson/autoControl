package com.mikadev.autocontrolapi.sparePartUsed;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SparePartUsedPostDTO(
        @NotNull(message = "Repair order ID is required")
        Long repairOrderId,

        @NotNull(message = "Spare part ID is required")
        Long sparePartId,

        @NotNull(message = "Unit price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Unit price must be positive")
        BigDecimal unitPrice,

        @Min(value = 1, message = "Quantity must be at least 1")
        int quantity,

        @NotNull(message = "Total price is required")
        @DecimalMin(value = "0.0", inclusive = true, message = "Total price cannot be negative")
        BigDecimal totalPrice,

        @NotNull(message = "Created by user ID is required")
        Long createdById
) {
}

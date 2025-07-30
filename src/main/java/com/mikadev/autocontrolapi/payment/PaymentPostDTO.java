package com.mikadev.autocontrolapi.payment;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentPostDTO(
        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be positive")
        BigDecimal amount,

        @NotNull(message = "Repair order ID is required")
        Long repairOrderId,

        @NotNull(message = "Payment date is required")
        LocalDateTime date,

        Long paymentStatusId,

        @NotNull(message = "Payment type ID is required")
        Long paymentTypeId,

        @NotNull(message = "Created by is required")
        Long createdBy
) {
}

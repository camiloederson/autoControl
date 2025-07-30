package com.mikadev.autocontrolapi.payment;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentPutDTO(
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

        @NotNull(message = "Updated by is required")
        Long updatedBy
) {}

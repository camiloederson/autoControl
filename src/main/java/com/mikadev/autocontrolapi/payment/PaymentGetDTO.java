package com.mikadev.autocontrolapi.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentGetDTO(
        Long id,
        BigDecimal amount,
        Long repairOrderId,
        LocalDateTime date,
        Long paymentStatusId,
        Long paymentTypeId,
        Long createdBy,
        Long updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

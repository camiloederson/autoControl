package com.mikadev.autocontrolapi.paymentStatus;

import java.time.LocalDateTime;

public record PaymentStatusGetDTO(
        Long id,
        String status,
        String description,
        Long createdBy,
        LocalDateTime createdAt,
        Long updatedBy,
        LocalDateTime updatedAt
) {
}

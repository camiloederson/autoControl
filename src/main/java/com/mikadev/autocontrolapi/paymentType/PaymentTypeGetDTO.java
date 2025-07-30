package com.mikadev.autocontrolapi.paymentType;

import java.time.LocalDateTime;

public record PaymentTypeGetDTO(
        Long id,
        String name,
        String description,
        Long createdBy,
        LocalDateTime createdAt,
        Long updatedBy,
        LocalDateTime updatedAt
) {
}

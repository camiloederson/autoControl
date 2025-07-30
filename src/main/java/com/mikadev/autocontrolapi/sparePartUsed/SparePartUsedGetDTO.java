package com.mikadev.autocontrolapi.sparePartUsed;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SparePartUsedGetDTO(
        Long id,
        Long repairOrderId,
        Long sparePartId,
        BigDecimal unitPrice,
        int quantity,
        BigDecimal totalPrice,
        Long createdById,
        LocalDateTime createdAt,
        Long updatedById,
        LocalDateTime updatedAt
) {
}

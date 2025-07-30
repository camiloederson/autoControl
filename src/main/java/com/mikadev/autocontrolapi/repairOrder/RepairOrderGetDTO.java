package com.mikadev.autocontrolapi.repairOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RepairOrderGetDTO(
        Long id,
        Long customerId,
        Long vehicleId,
        Long mechanicId,
        BigDecimal estimatedValue,
        BigDecimal finalValue,
        String diagnosis,
        String reportedIssue,
        String workDescription,
        String observations,
        Long statusId,
        LocalDateTime entryDate,
        LocalDateTime exitDate,
        Boolean isClosed,
        Boolean invoiceGenerated,
        Long createdBy,
        Long updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}

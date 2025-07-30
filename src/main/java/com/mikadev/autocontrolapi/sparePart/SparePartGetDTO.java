package com.mikadev.autocontrolapi.sparePart;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SparePartGetDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        int stock,
        String categoryName,
        String brandName,
        String model,
        boolean active,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

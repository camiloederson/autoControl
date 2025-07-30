package com.mikadev.autocontrolapi.sparePartCategory;

import java.time.LocalDateTime;

public record SparePartCategoryGetDTO(
        Long id,
        String name,
        String description,
        String createdBy,
        String updatedBy,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

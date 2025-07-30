package com.mikadev.autocontrolapi.sparePartBrand;

import java.time.LocalDateTime;

public record SparePartBrandGetDTO(
        Long id,
        String name,
        String description,
        String createdByUsername,
        String updatedByUsername,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

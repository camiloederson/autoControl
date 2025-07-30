package com.mikadev.autocontrolapi.sparePartCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SparePartCategoryPutDTO(
        @NotBlank(message = "Name is required")
        @Size(max = 50, message = "Name must not exceed 50 characters")
        String name,

        @NotBlank(message = "Description is required")
        @Size(min = 10, max = 255, message = "Description must be between 10 and 255 characters")
        String description,

        @NotNull(message = "UpdatedBy is required")
        Long updatedBy
) {
}

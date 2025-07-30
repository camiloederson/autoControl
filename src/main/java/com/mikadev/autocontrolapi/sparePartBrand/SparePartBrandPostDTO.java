package com.mikadev.autocontrolapi.sparePartBrand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SparePartBrandPostDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "CreatedBy is required")
        Long createdBy
) {
}

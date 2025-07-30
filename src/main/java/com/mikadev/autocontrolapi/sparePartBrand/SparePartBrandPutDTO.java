package com.mikadev.autocontrolapi.sparePartBrand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SparePartBrandPutDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "UpdatedBy is required")
        Long updatedBy
) {
}

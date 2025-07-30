package com.mikadev.autocontrolapi.sparePart;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record SparePartPostDTO(
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must not exceed 100 characters")
        String name,

        @NotBlank(message = "Description is required")
        @Size(max = 255, message = "Description must not exceed 255 characters")
        String description,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
        BigDecimal price,

        @Min(value = 0, message = "Stock cannot be negative")
        int stock,

        @NotNull(message = "Category ID is required")
        Long categoryId,

        @NotNull(message = "Brand ID is required")
        Long brandId,

        @Size(max = 100, message = "Model must not exceed 100 characters")
        String model,

        @NotNull(message = "CreatedBy is required")
        Long createdBy
) {
}

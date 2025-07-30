package com.mikadev.autocontrolapi.role;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.catalina.User;

public record RoleUpdateDTO(
        @NotBlank(message = "Role name is required")
        @Size(message = "Role name's length cannot exceed 25 characters")
        String name,

        @NotBlank(message = "Role description is required")
        @Size(message = "Role description must be between 50 and 200 characters")
        String description,

        @NotNull(message = "UpdatedBy is required")
        @Min(1)
        Long updatedById
) {
}

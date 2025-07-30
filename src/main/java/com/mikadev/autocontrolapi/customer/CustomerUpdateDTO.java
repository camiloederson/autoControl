package com.mikadev.autocontrolapi.customer;

import jakarta.validation.constraints.*;

public record CustomerUpdateDTO(
        @NotBlank(message = "Name is required")
        @Size(message = "Name length must not exceed 25 chars", max = 25)
        String name,

        @NotBlank(message = "Surname is required")
        @Size(max = 50, message = "Surname length must not exceed 50 characters")
        String surname,

        @NotBlank(message = "Dui is required")
        @Size(min = 9, max = 9, message = "DUI must have 9 digits")
        String dui,

        @Size(message = "Email length must not be exceed 50 characters", max = 50)
        @Email(message = "Email format is required")
        String email,

        @NotBlank(message = "Phone is required")
        @Size(min = 8, max = 8, message = "Phone must have exactly 8 digits")
        String phone,

        @NotNull(message = "UpdatedBy must not be null")
        Long updatedById
) {
}

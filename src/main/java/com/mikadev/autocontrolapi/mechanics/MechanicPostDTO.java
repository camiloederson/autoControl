package com.mikadev.autocontrolapi.mechanics;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record MechanicPostDTO(
        @NotBlank(message = "Name is required")
        @Size(message = "Name's length cannot be more than 25 characters", max = 25)
        String name,

        @NotBlank(message = "Name is required")
        @Size(message = "Name's length cannot be more than 50 characters", max = 50)
        String surname,

        @NotBlank(message = "Email is required")
        @Email(message = "Email format is required")
        String email,

        @NotBlank(message = "Phone number is required")
                @Size(max = 8, min = 8, message = "Phone number must to have exactly 8 digits")
        String phone,

        @NotNull(message = "Entry date is required.")
        LocalDate dateIn,

        LocalDate dateOut,

        @NotNull(message = "Active is required")
        Boolean active,

        @NotNull(message = "Birthdate is required")
        LocalDate birthDate,

        @NotNull(message = "CreatedBy id is required")
        Long createdById
) {
}

package com.mikadev.autocontrolapi.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserPostDTO(
        @NotBlank(message = "Name is required")
        @Size(message = "Name's length cannot exceed 25 characters")
        String name,

        @NotBlank(message = "Surname is required")
        @Size(message = "Surname's length cannot exceed 25 characters")
        String surname,

        @NotBlank(message = "Email is required")
        @Email(message = "Email format required")
        String email,

        @NotBlank(message = "Username is required")
        String userName,

        @NotNull(message = "CreatedBy id is required")
        Long createdById,

        @NotNull(message = "Role id is required")
        Long roleId,

        @NotBlank(message = "Password is required")
        String password
) {
}

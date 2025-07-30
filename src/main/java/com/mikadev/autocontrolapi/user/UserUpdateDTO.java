package com.mikadev.autocontrolapi.user;

import com.mikadev.autocontrolapi.role.RoleEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(
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

        @NotBlank(message = "Password id is required")
        String password,

        @NotNull(message = "Role id is required")
        Long roleId,

        @NotNull(message = "UpdatedBy id is required")
        Long updatedById
) {
}

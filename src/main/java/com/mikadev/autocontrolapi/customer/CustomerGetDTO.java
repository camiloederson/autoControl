package com.mikadev.autocontrolapi.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerGetDTO(
        Long id,
        String name,
        String surname,
        String dui,
        String email,
        String phone,
        boolean active
) {
}

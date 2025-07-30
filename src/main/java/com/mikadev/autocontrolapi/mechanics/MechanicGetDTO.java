package com.mikadev.autocontrolapi.mechanics;

import java.time.LocalDate;

public record MechanicGetDTO(
        Long id,
        String name,
        String surname,
        String email,
        String phone,
        LocalDate dateIn,
        LocalDate dateOut,
        boolean active,
        LocalDate birthdate
) {

}

package com.mikadev.autocontrolapi.user;

public record UserGetDTO(
        Long id,
        String name,
        String surname,
        String email,
        String userName,
        RoleNameDTO role) {
}

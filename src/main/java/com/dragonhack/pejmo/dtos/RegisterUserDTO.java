package com.dragonhack.pejmo.dtos;

public record RegisterUserDTO(
        String username,
        String password,
        String firstName,
        String lastName,
        String email
) {
}

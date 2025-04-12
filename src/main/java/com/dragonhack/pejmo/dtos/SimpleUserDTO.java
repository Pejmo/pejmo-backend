package com.dragonhack.pejmo.dtos;

import java.util.List;

public record SimpleUserDTO(
        String username,
        String firstName,
        String lastName,
        double averageRating
) {
}

package com.dragonhack.pejmo.dtos;

import java.time.LocalDateTime;

public record RideInput(
        Long userId,
        String fromLocation,
        String toLocation,
        LocalDateTime startTime,
        Double price,
        Integer freeSeats
) {
}

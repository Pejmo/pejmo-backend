package com.dragonhack.pejmo.dtos;

import java.time.LocalDateTime;

public record RideOutputDTO(
        SimpleUserDTO driver,
        String fromLocation,
        String toLocation,
        LocalDateTime startTime,
        Double price,
        Integer allSeats
) {
}

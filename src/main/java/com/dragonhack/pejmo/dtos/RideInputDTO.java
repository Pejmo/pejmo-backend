package com.dragonhack.pejmo.dtos;

import java.time.LocalDateTime;

public record RideInputDTO(
        Long driverId,
        String fromLocation,
        String toLocation,
        LocalDateTime startTime,
        Double price,
        Integer allSeats
) {
}

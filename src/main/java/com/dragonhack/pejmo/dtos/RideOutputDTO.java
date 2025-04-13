package com.dragonhack.pejmo.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record RideOutputDTO(
        Long id,
        SimpleUserDTO driver,
        String fromLocation,
        String toLocation,
        LocalDateTime startTime,
        Double price,
        Integer allSeats,
        Integer takenSeats,
        List<LocationPointDTO> pickUpPoints
) {
}

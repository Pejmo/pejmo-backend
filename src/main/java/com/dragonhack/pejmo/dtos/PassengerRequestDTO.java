package com.dragonhack.pejmo.dtos;

public record PassengerRequestDTO(
    Long id,
    SimpleUserDTO passenger,
    Long rideListingId,
    String requestStatus
) {
}

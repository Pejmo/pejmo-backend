package com.dragonhack.pejmo.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record PassengerGetDTO(Long id, String from, String to, String firstName, String lastName, String username, double averageRating, double price, LocalDateTime startTime, List<RideOfferDTO> driverOffers) {
}

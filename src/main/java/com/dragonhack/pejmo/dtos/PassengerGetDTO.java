package com.dragonhack.pejmo.dtos;

import java.time.LocalDateTime;

public record PassengerGetDTO(String firstName, String lastName, double averageRating, double price, LocalDateTime startTime) {
}

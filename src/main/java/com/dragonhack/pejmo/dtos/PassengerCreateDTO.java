package com.dragonhack.pejmo.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PassengerCreateDTO(
        @NotBlank(message = "From location is required")
        String fromLocation,

        @NotBlank(message = "To location is required")
        String toLocation,

        @NotNull(message = "Start time is required")
        @Future(message = "Start time must be in the future")
        LocalDateTime startTime,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        Double price,

        @NotNull(message = "Seats needed is required")
        @DecimalMin(value = "1.0", message = "At least one seat must be needed")
        Double seatsNeeded,

        @NotBlank(message = "Username is required")
        String username
) {}

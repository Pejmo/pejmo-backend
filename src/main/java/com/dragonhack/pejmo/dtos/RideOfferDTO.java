package com.dragonhack.pejmo.dtos;

import com.dragonhack.pejmo.models.OfferStatus;

public record RideOfferDTO(Long id, String firstName, String secondName, String username, OfferStatus offerStatus) {
}

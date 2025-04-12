package com.dragonhack.pejmo.dtos;

import java.util.List;

public record UserGetDTO(String firstName, String lastName, double averageRating, List<ReviewGetDTO> reviews, String kycStatus) {
}

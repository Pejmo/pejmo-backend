package com.dragonhack.pejmo.dtos;

import java.util.List;

public record UserGetDTO(String firstName, String lastName, String username, double averageRating, List<ReviewGetDTO> reviews, String kycStatus) {
}

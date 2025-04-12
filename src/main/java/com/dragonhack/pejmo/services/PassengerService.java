package com.dragonhack.pejmo.services;

import com.dragonhack.pejmo.dtos.PassengerGetDTO;
import com.dragonhack.pejmo.exceptions.resource_not_found.ResourceNotFoundException;
import com.dragonhack.pejmo.models.PassengerListing;
import com.dragonhack.pejmo.models.User;
import com.dragonhack.pejmo.repositories.PassengerRepository;
import com.dragonhack.pejmo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public PassengerService(PassengerRepository passengerRepository, UserService userService, UserRepository userRepository) {
        this.passengerRepository = passengerRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public List<PassengerGetDTO> getAllPassengers() {
        return passengerRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    private PassengerGetDTO convertToDTO(PassengerListing passengerListing) {
        return new PassengerGetDTO(
                passengerListing.getPassenger().getFirstName(),
                passengerListing.getPassenger().getLastName(),
                userService.getAverageRating(passengerListing.getPassenger()),
                passengerListing.getPrice(),
                passengerListing.getStartTime()
        );
    }

    public PassengerGetDTO getPassengerById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + userId + " not found"));
        return null;
    }

    public String createPassenger() {
        return "createPassenger";
    }

    public String invitePassenger(Long id) {
        return "invitePassenger";
    }

    public String deletePassenger(Long id) {
        return "deletePassenger";
    }
}

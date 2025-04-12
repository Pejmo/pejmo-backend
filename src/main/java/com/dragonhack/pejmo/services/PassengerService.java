package com.dragonhack.pejmo.services;

import com.dragonhack.pejmo.dtos.PassengerCreateDTO;
import com.dragonhack.pejmo.dtos.PassengerGetDTO;
import com.dragonhack.pejmo.dtos.RideOfferDTO;
import com.dragonhack.pejmo.exceptions.resource_not_found.ResourceNotFoundException;
import com.dragonhack.pejmo.models.PassengerListing;
import com.dragonhack.pejmo.models.User;
import com.dragonhack.pejmo.repositories.PassengerRepository;
import com.dragonhack.pejmo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public List<PassengerGetDTO> getAllPassengers(String fromLocation, String toLocation, LocalDateTime startTime) {
        List<PassengerListing> listings = passengerRepository.findAllFiltered(fromLocation, toLocation, startTime);
        return listings.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private PassengerGetDTO convertToDTO(PassengerListing passengerListing) {
        return new PassengerGetDTO(
                passengerListing.getFromLocation(),
                passengerListing.getToLocation(),
                passengerListing.getPassenger().getFirstName(),
                passengerListing.getPassenger().getLastName(),
                passengerListing.getPassenger().getUsername(),
                userService.getAverageRating(passengerListing.getPassenger()),
                passengerListing.getPrice(),
                passengerListing.getStartTime(),
                null
        );
    }

    public PassengerGetDTO getPassengerListingByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User with username " + username + " not found")
        );

        PassengerListing passengerListing = passengerRepository.findByPassenger(user).orElseThrow(
                () -> new ResourceNotFoundException("Passenger listing for user " + username + " not found")
        );

        List<RideOfferDTO> rideOffers = passengerListing.getDriverOffers().stream()
                .map(offer -> new RideOfferDTO(
                        offer.getDriver().getFirstName(),
                        offer.getDriver().getLastName(),
                        offer.getDriver().getUsername()
                ))
                .toList();

        return new PassengerGetDTO(
                passengerListing.getFromLocation(),
                passengerListing.getToLocation(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                userService.getAverageRating(user),
                passengerListing.getPrice(),
                passengerListing.getStartTime(),
                rideOffers
        );
    }

    public void createPassenger(PassengerCreateDTO dto) {
        User user = userRepository.findByUsername(dto.username()).orElseThrow(
                () -> new ResourceNotFoundException("User with username " + dto.username() + " not found")
        );

        PassengerListing listing = new PassengerListing();
        listing.setFromLocation(dto.fromLocation());
        listing.setToLocation(dto.toLocation());
        listing.setStartTime(dto.startTime());
        listing.setPrice(dto.price());
        listing.setSeatsNeeded(dto.seatsNeeded());
        listing.setPassenger(user);

        passengerRepository.save(listing);
    }

    public String invitePassenger(Long id) {
        return "invitePassenger";
    }

    public String deletePassenger(Long id) {
        return "deletePassenger";
    }
}

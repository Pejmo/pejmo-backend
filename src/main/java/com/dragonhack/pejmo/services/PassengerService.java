package com.dragonhack.pejmo.services;

import com.dragonhack.pejmo.dtos.PassengerCreateDTO;
import com.dragonhack.pejmo.dtos.PassengerGetDTO;
import com.dragonhack.pejmo.dtos.RideOfferDTO;
import com.dragonhack.pejmo.exceptions.conflict.ConflictException;
import com.dragonhack.pejmo.exceptions.resource_not_found.ResourceNotFoundException;
import com.dragonhack.pejmo.models.PassengerListing;
import com.dragonhack.pejmo.models.RideOffer;
import com.dragonhack.pejmo.models.User;
import com.dragonhack.pejmo.repositories.PassengerRepository;
import com.dragonhack.pejmo.repositories.RideOfferRepository;
import com.dragonhack.pejmo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final RideOfferRepository rideOfferRepository;

    public PassengerService(PassengerRepository passengerRepository, UserService userService, UserRepository userRepository, RideOfferRepository rideOfferRepository) {
        this.passengerRepository = passengerRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.rideOfferRepository = rideOfferRepository;
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
                        offer.getDriver().getUsername(),
                        offer.getOfferStatus()
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

        if (passengerRepository.findByPassenger(user).isPresent()) {
            throw new ConflictException("User " + dto.username() + " already has an existing passenger listing.");
        }

        PassengerListing listing = new PassengerListing();
        listing.setFromLocation(dto.fromLocation());
        listing.setToLocation(dto.toLocation());
        listing.setStartTime(dto.startTime());
        listing.setPrice(dto.price());
        listing.setSeatsNeeded(dto.seatsNeeded());
        listing.setPassenger(user);

        passengerRepository.save(listing);
    }

    public void offerRide(String username, long id) {
        User driver = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Driver not found"));

        PassengerListing passengerListing = passengerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Passenger listing not found"));

        if (driver.equals(passengerListing.getPassenger())) {
            throw new ConflictException("You cannot offer a ride to your own passenger listing.");
        }

        RideOffer rideOffer = new RideOffer();
        rideOffer.setDriver(driver);
        rideOffer.setPassengerListing(passengerListing);
        rideOffer.setOfferStatus("Pending");

        passengerListing.getDriverOffers().add(rideOffer);

        rideOfferRepository.save(rideOffer);
    }

    public String deletePassenger(Long id) {
        return "deletePassenger";
    }
}

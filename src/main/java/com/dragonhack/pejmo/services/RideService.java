package com.dragonhack.pejmo.services;

import com.dragonhack.pejmo.dtos.RideInputDTO;
import com.dragonhack.pejmo.dtos.RideOutputDTO;
import com.dragonhack.pejmo.dtos.SimpleUserDTO;
import com.dragonhack.pejmo.dtos.UserGetDTO;
import com.dragonhack.pejmo.exceptions.resource_not_found.ResourceNotFoundException;
import com.dragonhack.pejmo.models.RideListing;
import com.dragonhack.pejmo.models.User;
import com.dragonhack.pejmo.repositories.RideListingRepository;
import com.dragonhack.pejmo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RideService {
    private final RideListingRepository rideListingRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public RideService(RideListingRepository rideListingRepository, UserRepository userRepository, UserService userService) {
        this.rideListingRepository = rideListingRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<RideOutputDTO> getAllRides(
            String fromLocation,
            String toLocation,
            LocalDateTime startTime
    ) {

        List<RideListing> listings = rideListingRepository.findAllFiltered(fromLocation, toLocation, startTime);
        return listings.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private RideOutputDTO convertToDTO(RideListing rideListing) {
        User driver = rideListing.getDriver();
        SimpleUserDTO driverDTO = new SimpleUserDTO(
                driver.getUsername(),
                driver.getFirstName(),
                driver.getLastName(),
                userService.getAverageRating(driver)
        );
        return new RideOutputDTO(
                driverDTO,
                rideListing.getFromLocation(),
                rideListing.getToLocation(),
                rideListing.getStartTime(),
                rideListing.getPrice(),
                rideListing.getAllSeats()
        );
    }

    public RideListing getRideById(Long id) {
       return rideListingRepository.findById(id).
               orElseThrow(() -> new ResourceNotFoundException("Ride not found"));
    }

    public String getPaymentDetails(Long id) {
        return "getPaymentDetails";
    }

    public RideOutputDTO createRide(RideInputDTO rideInputDTO) {
        User driver = userRepository.findById(rideInputDTO.driverId()).
                orElseThrow(() -> new ResourceNotFoundException("User not found"));
        RideListing ride = new RideListing();
        ride.setDriver(driver);
        ride.setFromLocation(rideInputDTO.fromLocation());
        ride.setToLocation(rideInputDTO.toLocation());
        ride.setStartTime(rideInputDTO.startTime());
        ride.setPrice(rideInputDTO.price());
        ride.setAllSeats(rideInputDTO.allSeats());
        rideListingRepository.save(ride);
        return convertToDTO(ride);
    }

    public String bookRide(Long id) {
        return "bookRide";
    }

    public String deleteRide(Long id) {
        return "deleteRide";
    }
}
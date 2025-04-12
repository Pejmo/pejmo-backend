package com.dragonhack.pejmo.services;

import com.dragonhack.pejmo.dtos.*;
import com.dragonhack.pejmo.exceptions.conflict.ConflictException;
import com.dragonhack.pejmo.exceptions.resource_not_found.ResourceNotFoundException;
import com.dragonhack.pejmo.models.PassengerRequest;
import com.dragonhack.pejmo.models.RideListing;
import com.dragonhack.pejmo.models.User;
import com.dragonhack.pejmo.repositories.PassengerRepository;
import com.dragonhack.pejmo.repositories.PassengerRequestRepository;
import com.dragonhack.pejmo.repositories.RideListingRepository;
import com.dragonhack.pejmo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RideService {
    private final RideListingRepository rideListingRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PassengerRequestRepository passengerRequestRepository;
    private final PassengerRepository passengerRepository;

    public RideService(RideListingRepository rideListingRepository, UserRepository userRepository, UserService userService, PassengerRequestRepository passengerRequestRepository, PassengerRepository passengerRepository) {
        this.rideListingRepository = rideListingRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.passengerRequestRepository = passengerRequestRepository;
        this.passengerRepository = passengerRepository;
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

    public RideOutputDTO convertToDTO(RideListing rideListing) {
        User driver = rideListing.getDriver();
        SimpleUserDTO driverDTO = new SimpleUserDTO(
                driver.getUsername(),
                driver.getFirstName(),
                driver.getLastName(),
                userService.getAverageRating(driver)
        );
        return new RideOutputDTO(
                rideListing.getId(),
                driverDTO,
                rideListing.getFromLocation(),
                rideListing.getToLocation(),
                rideListing.getStartTime(),
                rideListing.getPrice(),
                rideListing.getAllSeats(),
                rideListing.getTakenSeats()
        );
    }

    public RideOutputDTO getRideById(Long id) {
       RideListing ride = rideListingRepository.findById(id).
               orElseThrow(() -> new ResourceNotFoundException("Ride Listing not found"));
       return convertToDTO(ride);
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
        ride.setTakenSeats(0);
        ride.setPassengers(new ArrayList<>());
        rideListingRepository.save(ride);
        return convertToDTO(ride);
    }

    public String bookRide(Long id) {
        return "bookRide";
    }

    public String deleteRide(Long id) {
        return "deleteRide";
    }

    public PassengerRequestDTO requestRide(String username, Long rideId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with username " + username + " not found"));
        RideListing ride = rideListingRepository.findById(rideId)
                .orElseThrow(() -> new ResourceNotFoundException("Ride listing with id " + rideId + " not found"));
        PassengerRequest request = new PassengerRequest();
        request.setPassenger(user);
        request.setRideListing(ride);
        request.setRequestStatus("pending");

        passengerRequestRepository.save(request);
        return convertToDTO(request);
    }

    public PassengerRequestDTO convertToDTO(PassengerRequest request) {
        User user = request.getPassenger();
        return new PassengerRequestDTO(
                request.getId(),
                new SimpleUserDTO(
                        user.getUsername(),
                        user.getFirstName(),
                        user.getLastName(),
                        userService.getAverageRating(user)
                ),
                request.getRideListing().getId(),
                request.getRequestStatus()
        );
    }

    public List<PassengerRequestDTO> getRequests(Long id) {

        RideListing ride = rideListingRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Ride Listing not found"));

        List<PassengerRequest> requests = passengerRequestRepository.findByRideListing(ride);
        return requests.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public PassengerRequestDTO approveRequest(Long requestId) {
        PassengerRequest request = passengerRequestRepository.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger request with id " + requestId + " not found"));
        if (request.getRequestStatus().equals("pending")) {
            RideListing ride = request.getRideListing();
            if (ride.getTakenSeats() >= ride.getAllSeats()) {
                throw new ConflictException("All seats are taken");
            }
            List<User> passengers = ride.getPassengers();
            passengers.add(request.getPassenger());
            ride.setTakenSeats(ride.getTakenSeats() + 1);
            rideListingRepository.save(ride);
            request.setRequestStatus("approved");
            passengerRequestRepository.save(request);
        }

        return convertToDTO(request);
    }


}
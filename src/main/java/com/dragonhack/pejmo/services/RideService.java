package com.dragonhack.pejmo.services;

import com.dragonhack.pejmo.dtos.*;
import com.dragonhack.pejmo.exceptions.conflict.ConflictException;
import com.dragonhack.pejmo.exceptions.resource_not_found.ResourceNotFoundException;
import com.dragonhack.pejmo.models.LocationPoint;
import com.dragonhack.pejmo.models.PassengerRequest;
import com.dragonhack.pejmo.models.RideListing;
import com.dragonhack.pejmo.models.User;
import com.dragonhack.pejmo.repositories.*;
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
    private final LocationPointRepository locationPointRepository;

    public RideService(RideListingRepository rideListingRepository, UserRepository userRepository, UserService userService, PassengerRequestRepository passengerRequestRepository, PassengerRepository passengerRepository, LocationPointRepository locationPointRepository) {
        this.rideListingRepository = rideListingRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.passengerRequestRepository = passengerRequestRepository;
        this.locationPointRepository = locationPointRepository;
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
        return new RideOutputDTO(
                rideListing.getId(),
                convertToDTO(rideListing.getDriver()),
                rideListing.getFromLocation(),
                rideListing.getToLocation(),
                rideListing.getStartTime(),
                rideListing.getPrice(),
                rideListing.getAllSeats(),
                rideListing.getTakenSeats(),
                rideListing.getPickUpPoints().stream()
                        .map(this::convertToDTO)
                        .toList()
        );
    }

    public LocationPointDTO convertToDTO(LocationPoint point) {
        return new LocationPointDTO(
                point.getLatitude(),
                point.getLongitude(),
                point.getName()
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
        User driver = userRepository.findByUsername(rideInputDTO.username()).
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
        ride.setPickUpPoints(new ArrayList<>());
        rideListingRepository.save(ride);
        return convertToDTO(ride);
    }

    public RideOutputDTO addPickUpPoint(Long id, LocationPointDTO point) {
        RideListing ride = rideListingRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Ride Listing not found"));
        List<LocationPoint> pickUpPoints = ride.getPickUpPoints();
        LocationPoint pickUpPoint = new LocationPoint();
        pickUpPoint.setRideListing(ride);
        pickUpPoint.setLatitude(point.latitude());
        pickUpPoint.setLongitude(point.longitude());
        pickUpPoint.setName(point.name());
        locationPointRepository.save(pickUpPoint);
        pickUpPoints.add(pickUpPoint);
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
                convertToDTO(user),
                request.getRideListing().getId(),
                request.getRequestStatus()
        );
    }

    public SimpleUserDTO convertToDTO(User user) {
        return new SimpleUserDTO(
                        user.getUsername(),
                        user.getFirstName(),
                        user.getLastName(),
                        userService.getAverageRating(user)
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

    public List<SimpleUserDTO> getPassengers(Long id) {
        RideListing ride = rideListingRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Ride Listing not found"));
        return ride.getPassengers().stream()
                .map(this::convertToDTO)
                .toList();
    }


}
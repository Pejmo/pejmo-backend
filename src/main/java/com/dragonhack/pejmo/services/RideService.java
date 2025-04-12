package com.dragonhack.pejmo.services;

import com.dragonhack.pejmo.dtos.RideInput;
import com.dragonhack.pejmo.exceptions.resource_not_found.ResourceNotFoundException;
import com.dragonhack.pejmo.models.RideListing;
import com.dragonhack.pejmo.models.User;
import com.dragonhack.pejmo.repositories.RideListingRepository;
import com.dragonhack.pejmo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {
    private final RideListingRepository rideListingRepository;
    private final UserRepository userRepository;

    public RideService(RideListingRepository rideListingRepository, UserRepository userRepository) {
        this.rideListingRepository = rideListingRepository;
        this.userRepository = userRepository;
    }

    public List<RideListing> getAllRides(


    ) {
        return rideListingRepository.findAll();
    }

    public RideListing getRideById(Long id) {
       return rideListingRepository.findById(id).
               orElseThrow(() -> new ResourceNotFoundException("Ride not found"));
    }

    public String getPaymentDetails(Long id) {
        return "getPaymentDetails";
    }

    public RideListing createRide(RideInput rideInput) {
        User driver = userRepository.findById(rideInput.driverId()).
                orElseThrow(() -> new ResourceNotFoundException("User not found"));
        RideListing ride = new RideListing();
        ride.setDriver(driver);
        ride.setFromLocation(rideInput.fromLocation());
        ride.setToLocation(rideInput.toLocation());
        ride.setStartTime(rideInput.startTime());
        ride.setPrice(rideInput.price());
        ride.setAllSeats(rideInput.allSeats());
        rideListingRepository.save(ride);
        return ride;
    }

    public String bookRide(Long id) {
        return "bookRide";
    }

    public String deleteRide(Long id) {
        return "deleteRide";
    }
}
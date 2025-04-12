package com.dragonhack.pejmo.services;

import com.dragonhack.pejmo.dtos.RideInput;
import com.dragonhack.pejmo.exceptions.resource_not_found.ResourceNotFoundException;
import com.dragonhack.pejmo.models.Ride;
import com.dragonhack.pejmo.models.User;
import com.dragonhack.pejmo.repositories.RideRepository;
import com.dragonhack.pejmo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {
    private final RideRepository rideRepository;
    private final UserRepository userRepository;

    public RideService(RideRepository rideRepository, UserRepository userRepository) {
        this.rideRepository = rideRepository;
        this.userRepository = userRepository;
    }

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public Ride getRideById(Long id) {
       return rideRepository.findById(id).
               orElseThrow(() -> new ResourceNotFoundException("Ride not found"));
    }

    public String getPaymentDetails(Long id) {
        return "getPaymentDetails";
    }

    public Ride createRide(RideInput rideInput) {
        User user = userRepository.findById(rideInput.userId()).
                orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Ride ride = new Ride();
        ride.setUser(user);
        ride.setfromLocation(rideInput.fromLocation());
        ride.settoLocation(rideInput.toLocation());
        ride.setStartTime(rideInput.startTime());
        ride.setPrice(rideInput.price());
        ride.setFreeSeats(rideInput.freeSeats());
        rideRepository.save(ride);
        return ride;
    }

    public String bookRide(Long id) {
        return "bookRide";
    }

    public String deleteRide(Long id) {
        return "deleteRide";
    }
}
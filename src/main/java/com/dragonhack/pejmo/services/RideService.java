package com.dragonhack.pejmo.services;

import com.dragonhack.pejmo.dtos.RideInput;
import com.dragonhack.pejmo.exceptions.resource_not_found.ResourceNotFoundException;
import com.dragonhack.pejmo.models.Ride;
import com.dragonhack.pejmo.repositories.RideRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {
    private final RideRepository rideRepository;

    public RideService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
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
        Ride ride = new Ride();
        return ride;
    }

    public String bookRide(Long id) {
        return "bookRide";
    }

    public String deleteRide(Long id) {
        return "deleteRide";
    }
}
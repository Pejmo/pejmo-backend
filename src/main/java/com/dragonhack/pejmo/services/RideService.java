package com.dragonhack.pejmo.services;

import com.dragonhack.pejmo.repositories.RideRepository;
import org.springframework.stereotype.Service;

@Service
public class RideService {
    private final RideRepository rideRepository;

    public RideService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public String getAllRides() {
        return "getAllRides";
    }

    public String getRideById(Long id) {
        return "getRideById";
    }

    public String getPaymentDetails(Long id) {
        return "getPaymentDetails";
    }

    public String createRide() {
        return "createRide";
    }

    public String bookRide(Long id) {
        return "bookRide";
    }

    public String deleteRide(Long id) {
        return "deleteRide";
    }
}
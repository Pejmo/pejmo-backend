package com.dragonhack.pejmo.controllers;

import com.dragonhack.pejmo.dtos.RideInput;
import com.dragonhack.pejmo.models.RideListing;
import com.dragonhack.pejmo.services.RideService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rides")
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping()
    public List<RideListing> getAllRides() {
        return rideService.getAllRides();
    }

    @GetMapping("/{id}")
    public RideListing getRideById(@PathVariable Long id) {
        return rideService.getRideById(id);
    }

    @GetMapping("/{id}/payment")
    public String getPaymentDetails(@PathVariable Long id) {
        return rideService.getPaymentDetails(id);
    }

    @PostMapping()
    public RideListing createRide(@RequestBody RideInput rideInput) {
        return rideService.createRide(rideInput);
    }

    @PostMapping("/{id}/book")
    public String bookRide(@PathVariable Long id) {
        return rideService.bookRide(id);
    }

    @DeleteMapping("/{id}")
    public String deleteRide(@PathVariable Long id) {
        return rideService.deleteRide(id);
    }
}

package com.dragonhack.pejmo.controllers;

import com.dragonhack.pejmo.dtos.RideInputDTO;
import com.dragonhack.pejmo.dtos.RideOutputDTO;
import com.dragonhack.pejmo.models.RideListing;
import com.dragonhack.pejmo.services.RideService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/rides")
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping()
    public List<RideOutputDTO> getAllRides(
            @RequestParam(required = false, defaultValue = "") String fromLocation,
            @RequestParam(required = false, defaultValue = "") String toLocation,
            @RequestParam(required = false) LocalDateTime startTime
    ) {
        if (startTime == null) {
            startTime = LocalDateTime.now();
        }
        return rideService.getAllRides(fromLocation, toLocation, startTime);
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
    public RideOutputDTO createRide(@RequestBody RideInputDTO rideInputDTO) {
        return rideService.createRide(rideInputDTO);
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

package com.dragonhack.pejmo.controllers;

import com.dragonhack.pejmo.dtos.PassengerRequestDTO;
import com.dragonhack.pejmo.dtos.RideInputDTO;
import com.dragonhack.pejmo.dtos.RideOutputDTO;
import com.dragonhack.pejmo.models.RideListing;
import com.dragonhack.pejmo.services.PassengerService;
import com.dragonhack.pejmo.services.RideService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/rides")
public class RideController {

    private final RideService rideService;
    private final PassengerService passengerService;

    public RideController(RideService rideService, PassengerService passengerService) {
        this.rideService = rideService;
        this.passengerService = passengerService;
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
    public RideOutputDTO getRideById(@PathVariable Long id) {
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

    @PostMapping("/{username}/request/{rideId}")
    public PassengerRequestDTO requestRide(@PathVariable String username, @PathVariable Long rideId) {
        return rideService.requestRide(username, rideId);
    }

    @GetMapping("/{id}/requests")
    public List<PassengerRequestDTO> getRequests(@PathVariable Long id) {
        return rideService.getRequests(id);
    }

    @PostMapping("/approve/{requestId}")
    public PassengerRequestDTO approveRequest(@PathVariable Long requestId) {
        return rideService.approveRequest(requestId);
    }
}

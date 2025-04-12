package com.dragonhack.pejmo.controllers;

import com.dragonhack.pejmo.dtos.PassengerCreateDTO;
import com.dragonhack.pejmo.dtos.PassengerGetDTO;
import com.dragonhack.pejmo.dtos.RideOfferDTO;
import com.dragonhack.pejmo.services.PassengerService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping()
    public List<PassengerGetDTO> getAllPassengers(
            @RequestParam(required = false, defaultValue = "") String fromLocation,
            @RequestParam(required = false, defaultValue = "") String toLocation,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime) {
        if (startTime == null)
            startTime = LocalDateTime.now();
        return passengerService.getAllPassengers(fromLocation, toLocation, startTime);
    }

    @GetMapping("/{username}")
    public PassengerGetDTO getPassengerById(@PathVariable String username) {
        return passengerService.getPassengerListingByUsername(username);
    }

    @PostMapping()
    public void createPassenger(@RequestBody PassengerCreateDTO dto) {
        passengerService.createPassenger(dto);
    }

    @PostMapping("/{username}/offer/{rideId}")
    public void offerRide(@PathVariable String username, @PathVariable long rideId) {
        passengerService.offerRide(username, rideId);
    }

    @PostMapping("/{username}/offer/{offerId}/accept")
    public void acceptOfferedRide(@PathVariable String username, @PathVariable long offerId) {
        passengerService.acceptOfferedRide(username, offerId);
    }

    @GetMapping("/offers/{username}/accepted")
    public List<RideOfferDTO> getAcceptedOffersByDriver(@PathVariable String username) {
        return passengerService.getAcceptedOffersByDriver(username);
    }

    @DeleteMapping("/{id}")
    public String deletePassenger(@PathVariable Long id) {
        return passengerService.deletePassenger(id);
    }
}

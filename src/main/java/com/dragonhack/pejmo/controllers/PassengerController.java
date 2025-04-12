package com.dragonhack.pejmo.controllers;

import com.dragonhack.pejmo.services.PassengerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping()
    public String getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/{id}")
    public String getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id);
    }

    @PostMapping()
    public String createPassenger() {
        return passengerService.createPassenger();
    }

    @PostMapping("/{id}/invite")
    public String invitePassenger(@PathVariable Long id) {
        return passengerService.invitePassenger(id);
    }

    @DeleteMapping("/{id}")
    public String deletePassenger(@PathVariable Long id) {
        return passengerService.deletePassenger(id);
    }
}

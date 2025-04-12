package com.dragonhack.pejmo.controllers;

import com.dragonhack.pejmo.dtos.PassengerCreateDTO;
import com.dragonhack.pejmo.dtos.PassengerGetDTO;
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

    @PostMapping("/{id}/invite")
    public String invitePassenger(@PathVariable Long id) {
        return passengerService.invitePassenger(id);
    }

    @DeleteMapping("/{id}")
    public String deletePassenger(@PathVariable Long id) {
        return passengerService.deletePassenger(id);
    }
}

package com.dragonhack.pejmo.services;

import com.dragonhack.pejmo.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public String getAllPassengers() {
        return "getAllPassengers";
    }

    public String getPassengerById(Long id) {
        return "getPassengerById";
    }

    public String createPassenger() {
        return "createPassenger";
    }

    public String invitePassenger(Long id) {
        return "invitePassenger";
    }

    public String deletePassenger(Long id) {
        return "deletePassenger";
    }
}

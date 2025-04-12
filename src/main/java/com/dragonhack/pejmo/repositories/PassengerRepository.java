package com.dragonhack.pejmo.repositories;

import com.dragonhack.pejmo.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}

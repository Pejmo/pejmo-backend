package com.dragonhack.pejmo.repositories;

import com.dragonhack.pejmo.models.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
}

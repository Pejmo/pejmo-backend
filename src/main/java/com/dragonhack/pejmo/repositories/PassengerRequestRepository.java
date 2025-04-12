package com.dragonhack.pejmo.repositories;

import com.dragonhack.pejmo.models.PassengerRequest;
import com.dragonhack.pejmo.models.RideListing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerRequestRepository extends JpaRepository<PassengerRequest, Long> {
    List<PassengerRequest> findByRideListing(RideListing rideListing);
}

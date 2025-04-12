package com.dragonhack.pejmo.repositories;

import com.dragonhack.pejmo.models.PassengerListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<PassengerListing, Long> {
}

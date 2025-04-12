package com.dragonhack.pejmo.repositories;

import com.dragonhack.pejmo.models.RideListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideListingRepository extends JpaRepository<RideListing, Long> {
}

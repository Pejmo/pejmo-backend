package com.dragonhack.pejmo.repositories;

import com.dragonhack.pejmo.models.OfferStatus;
import com.dragonhack.pejmo.models.RideOffer;
import com.dragonhack.pejmo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideOfferRepository extends JpaRepository<RideOffer, Long> {
    List<RideOffer> findByDriverAndOfferStatus(User driver, OfferStatus offerStatus);
    List<RideOffer> findByPassengerListing_PassengerAndOfferStatus(User passenger, OfferStatus offerStatus);

}

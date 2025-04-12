package com.dragonhack.pejmo.repositories;

import com.dragonhack.pejmo.models.PassengerListing;
import com.dragonhack.pejmo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<PassengerListing, Long> {
    Optional<PassengerListing> findByPassenger(User user);

    @Query("""
        SELECT r FROM PassengerListing r
        WHERE (:fromLocation = '' OR r.fromLocation = :fromLocation)
        AND (:toLocation = '' OR r.toLocation = :toLocation)
        AND (r.startTime >= :fromTime)
        ORDER BY r.startTime ASC
        """
    )
    List<PassengerListing> findAllFiltered(
            @Param("fromLocation") String fromLocation,
            @Param("toLocation") String toLocation,
            @Param("fromTime") LocalDateTime fromTime
    );
}

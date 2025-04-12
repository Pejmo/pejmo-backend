package com.dragonhack.pejmo.repositories;

import com.dragonhack.pejmo.models.RideListing;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RideListingRepository extends JpaRepository<RideListing, Long> {
    @Query("""
        SELECT r FROM RideListing r
        WHERE (:fromLocation = '' OR r.fromLocation = :fromLocation)
        AND (:toLocation = '' OR r.toLocation = :toLocation)
        AND (r.startTime >= :startTime)
        ORDER BY r.startTime ASC
        """
    )
    List<RideListing> findAllFiltered(
           @Param("fromLocation") String fromLocation,
           @Param("toLocation") String toLocation,
           @Param("startTime") LocalDateTime startTime
    );
}

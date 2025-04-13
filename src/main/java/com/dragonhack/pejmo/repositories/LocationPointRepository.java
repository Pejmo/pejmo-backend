package com.dragonhack.pejmo.repositories;

import com.dragonhack.pejmo.models.LocationPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationPointRepository extends JpaRepository<LocationPoint, Long> {
}

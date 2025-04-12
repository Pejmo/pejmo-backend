package com.dragonhack.pejmo.repositories;

import com.dragonhack.pejmo.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

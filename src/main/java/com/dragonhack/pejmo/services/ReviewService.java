package com.dragonhack.pejmo.services;

import com.dragonhack.pejmo.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public String getReviewsByUser(Long userId) {
        return "getReviewsByUser";
    }

    public String deleteReview(Long id) {
        return "deleteReview";
    }
}

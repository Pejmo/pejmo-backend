package com.dragonhack.pejmo.services;

import com.dragonhack.pejmo.dtos.ReviewGetDTO;
import com.dragonhack.pejmo.dtos.UserGetDTO;
import com.dragonhack.pejmo.exceptions.resource_not_found.ResourceNotFoundException;
import com.dragonhack.pejmo.models.Review;
import com.dragonhack.pejmo.models.User;
import com.dragonhack.pejmo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserGetDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " not found"));
        return new UserGetDTO(user.getFirstName(), user.getLastName(), getAverageRating(user), getReviewsForUser(user), user.getKycStatus());
    }

    public String getCurrentUser() {
        return "getCurrentUser";
    }

    public String updateUser(Long id) {
        return "updateUser";
    }

    public String loginUser() {
        return "loginUser";
    }

    public String registerUser() {
        return "registerUser";
    }

    public String deleteUser(Long id) {
        return "deleteUser";
    }

    public double getAverageRating(User user) {
        List<Review> reviews = user.getReviewsReceived();

        if (reviews == null || reviews.isEmpty()) {
            return -1.0;
        }

        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }

    private List<ReviewGetDTO> getReviewsForUser(User user) {
        return user.getReviewsReceived().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ReviewGetDTO convertToDTO(Review review) {
        return new ReviewGetDTO(
                review.getFromUser().getFirstName(),
                review.getFromUser().getLastName(),
                review.getRating(),
                review.getContent()
        );
    }
}

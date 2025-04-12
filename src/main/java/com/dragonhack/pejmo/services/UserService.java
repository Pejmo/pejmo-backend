package com.dragonhack.pejmo.services;

import com.dragonhack.pejmo.dtos.RegisterUserDTO;
import com.dragonhack.pejmo.dtos.ReviewGetDTO;
import com.dragonhack.pejmo.dtos.UserGetDTO;
import com.dragonhack.pejmo.exceptions.conflict.ConflictException;
import com.dragonhack.pejmo.exceptions.resource_not_found.ResourceNotFoundException;
import com.dragonhack.pejmo.models.Review;
import com.dragonhack.pejmo.models.User;
import com.dragonhack.pejmo.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserGetDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User with username " + username + " not found"));
        return new UserGetDTO(user.getFirstName(), user.getLastName(), user.getUsername(), getAverageRating(user), getReviewsForUser(user), user.getKycStatus());
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

    public void registerUser(RegisterUserDTO dto) {
        if (userRepository.existsByUsername(dto.username()) || userRepository.existsByEmail(dto.email())) {
            throw new ConflictException("Username or email already exists");
        }

        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setKycStatus("pending");

        userRepository.save(user);
    }

    public String deleteUser(Long id) {
        return "deleteUser";
    }

    public double getAverageRating(User user) {
        List<Review> reviews = user.getReviewsReceived();

        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
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

package com.dragonhack.pejmo.init;

import com.dragonhack.pejmo.models.PassengerListing;
import com.dragonhack.pejmo.models.Review;
import com.dragonhack.pejmo.models.User;
import com.dragonhack.pejmo.repositories.PassengerRepository;
import com.dragonhack.pejmo.repositories.ReviewRepository;
import com.dragonhack.pejmo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DatabaseInitializer {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   ReviewRepository reviewRepository,
                                   PasswordEncoder passwordEncoder,
                                   PassengerRepository passengerRepository) {
        return args -> {
            // Create Users
            User alice = new User();
            alice.setUsername("alice");
            alice.setPassword(passwordEncoder.encode("password1"));
            alice.setFirstName("Alice");
            alice.setLastName("Wonderland");
            alice.setEmail("alice@example.com");
            alice.setKycStatus("pending");

            User bob = new User();
            bob.setUsername("bob");
            bob.setPassword(passwordEncoder.encode("password2"));
            bob.setFirstName("Bob");
            bob.setLastName("Builder");
            bob.setEmail("bob@example.com");
            bob.setKycStatus("validated");

            userRepository.saveAll(List.of(alice, bob));

            // Create Reviews
            Review r1 = new Review();
            r1.setFromUser(bob);
            r1.setToUser(alice);
            r1.setContent("Great collaborator, very responsive!");
            r1.setRating(5);

            Review r2 = new Review();
            r2.setFromUser(alice);
            r2.setToUser(bob);
            r2.setContent("Delivered late, but solid work.");
            r2.setRating(4);

            reviewRepository.saveAll(List.of(r1, r2));

            // Create PassengerListings
            PassengerListing passengerListing1 = new PassengerListing();
            passengerListing1.setFromLocation("New York");
            passengerListing1.setToLocation("Los Angeles");
            passengerListing1.setStartTime(LocalDateTime.now().plusDays(1));
            passengerListing1.setPrice(125.0);
            passengerListing1.setSeatsNeeded(2.0);
            passengerListing1.setPassenger(bob);

            PassengerListing passengerListing2 = new PassengerListing();
            passengerListing2.setFromLocation("Chicago");
            passengerListing2.setToLocation("San Francisco");
            passengerListing2.setStartTime(LocalDateTime.now().plusDays(3));
            passengerListing2.setPrice(150.0);
            passengerListing2.setSeatsNeeded(3.0);
            passengerListing2.setPassenger(alice);

            // Save passenger listings
            passengerRepository.saveAll(List.of(passengerListing1, passengerListing2));
        };
    }
}

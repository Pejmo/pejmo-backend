package com.dragonhack.pejmo.init;

import com.dragonhack.pejmo.models.*;
import com.dragonhack.pejmo.repositories.*;
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
                                   RideListingRepository rideListingRepository,
                                   ReviewRepository reviewRepository,
                                   PasswordEncoder passwordEncoder,
                                   PassengerRepository passengerRepository,
                                   LocationPointRepository locationPointRepository) {
        return args -> {
            // Create Users
            User alice = new User();
            alice.setUsername("alenka");
            alice.setPassword(passwordEncoder.encode("password1"));
            alice.setFirstName("Alenka");
            alice.setLastName("Čudežna");
            alice.setEmail("alenka@example.com");
            alice.setKycStatus("pending");

            User bob = new User();
            bob.setUsername("bojan");
            bob.setPassword(passwordEncoder.encode("password2"));
            bob.setFirstName("Bojan");
            bob.setLastName("Gradbenik");
            bob.setEmail("bojan@example.com");
            bob.setKycStatus("validated");

            User tom = new User();
            tom.setUsername("tomaz");
            tom.setPassword(passwordEncoder.encode("password3"));
            tom.setFirstName("Tomaž");
            tom.setLastName("Gradbenik");
            tom.setEmail("tomaz@example.com");
            tom.setKycStatus("validated");

            User veno = new User();
            veno.setUsername("niko");
            veno.setPassword(passwordEncoder.encode("password4"));
            veno.setFirstName("Niko");
            veno.setLastName("Niki");
            veno.setEmail("niko@example.com");
            veno.setKycStatus("pending");

            userRepository.saveAll(List.of(alice, bob, tom, veno));

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

            Review r3 = new Review();
            r3.setFromUser(alice);
            r3.setToUser(bob);
            r3.setContent("Bad driver");
            r3.setRating(2);

            reviewRepository.saveAll(List.of(r1, r2, r3));

            // Create PassengerListings
            PassengerListing passengerListing1 = new PassengerListing();
            passengerListing1.setFromLocation("Koper");
            passengerListing1.setToLocation("Ljubljana");
            passengerListing1.setStartTime(LocalDateTime.now().plusDays(1).withMinute(0).withSecond(0).withNano(0));
            passengerListing1.setPrice(7.0);
            passengerListing1.setSeatsNeeded(2.0);
            passengerListing1.setPassenger(bob);

            PassengerListing passengerListing2 = new PassengerListing();
            passengerListing2.setFromLocation("Ljubljana");
            passengerListing2.setToLocation("Koper");
            passengerListing2.setStartTime(LocalDateTime.now().plusDays(3).withMinute(0).withSecond(0).withNano(0));
            passengerListing2.setPrice(8.0);
            passengerListing2.setSeatsNeeded(3.0);
            passengerListing2.setPassenger(alice);

            PassengerListing passengerListing3 = new PassengerListing();
            passengerListing3.setFromLocation("Maribor");
            passengerListing3.setToLocation("Celje");
            passengerListing3.setStartTime(LocalDateTime.now().plusDays(2).withMinute(0).withSecond(0).withNano(0));
            passengerListing3.setPrice(6.0);
            passengerListing3.setSeatsNeeded(1.0);
            passengerListing3.setPassenger(bob);

            PassengerListing passengerListing6 = new PassengerListing();
            passengerListing6.setFromLocation("Ptuj");
            passengerListing6.setToLocation("Maribor");
            passengerListing6.setStartTime(LocalDateTime.now().plusDays(2).plusHours(3).withMinute(0).withSecond(0).withNano(0));
            passengerListing6.setPrice(9.0);
            passengerListing6.setSeatsNeeded(1.0);
            passengerListing6.setPassenger(alice);

            PassengerListing passengerListing7 = new PassengerListing();
            passengerListing7.setFromLocation("Murska Sobota");
            passengerListing7.setToLocation("Ljubljana");
            passengerListing7.setStartTime(LocalDateTime.now().plusDays(5).withMinute(0).withSecond(0).withNano(0));
            passengerListing7.setPrice(10.0);
            passengerListing7.setSeatsNeeded(2.0);
            passengerListing7.setPassenger(bob);

            PassengerListing passengerListing8 = new PassengerListing();
            passengerListing8.setFromLocation("Kranj");
            passengerListing8.setToLocation("Maribor");
            passengerListing8.setStartTime(LocalDateTime.now().plusDays(1).withMinute(0).withSecond(0).withNano(0));
            passengerListing8.setPrice(5.0);
            passengerListing8.setSeatsNeeded(1.0);
            passengerListing8.setPassenger(alice);

            PassengerListing passengerListing9 = new PassengerListing();
            passengerListing9.setFromLocation("Celje");
            passengerListing9.setToLocation("Ptuj");
            passengerListing9.setStartTime(LocalDateTime.now().plusDays(3).withMinute(0).withSecond(0).withNano(0));
            passengerListing9.setPrice(7.5);
            passengerListing9.setSeatsNeeded(2.0);
            passengerListing9.setPassenger(bob);

            passengerRepository.saveAll(List.of(
                    passengerListing1,
                    passengerListing2,
                    passengerListing3,
                    passengerListing6,
                    passengerListing7,
                    passengerListing8,
                    passengerListing9
            ));

            RideListing ride1 = new RideListing();
            ride1.setFromLocation("Ljubljana");
            ride1.setToLocation("Maribor");
            ride1.setStartTime(LocalDateTime.now().plusDays(1).withMinute(0).withSecond(0).withNano(0));
            ride1.setPrice(9.0);
            ride1.setAllSeats(4);
            ride1.setTakenSeats(1);
            ride1.setDriver(bob);
            ride1.setPassengers(List.of(alice));

            RideListing ride2 = new RideListing();
            ride2.setFromLocation("Celje");
            ride2.setToLocation("Kranj");
            ride2.setStartTime(LocalDateTime.now().plusDays(2).withMinute(0).withSecond(0).withNano(0));
            ride2.setPrice(6.5);
            ride2.setAllSeats(3);
            ride2.setTakenSeats(2);
            ride2.setDriver(alice);
            ride2.setPassengers(List.of(bob, alice));

            RideListing ride3 = new RideListing();
            ride3.setFromLocation("Koper");
            ride3.setToLocation("Ljubljana");
            ride3.setStartTime(LocalDateTime.now().plusDays(3).withMinute(0).withSecond(0).withNano(0));
            ride3.setPrice(5.5);
            ride3.setAllSeats(4);
            ride3.setTakenSeats(0);
            ride3.setDriver(bob);

            RideListing ride4 = new RideListing();
            ride4.setFromLocation("Ptuj");
            ride4.setToLocation("Celje");
            ride4.setStartTime(LocalDateTime.now().plusDays(1).plusHours(5).withMinute(0).withSecond(0).withNano(0));
            ride4.setPrice(7.5);
            ride4.setAllSeats(2);
            ride4.setTakenSeats(1);
            ride4.setDriver(alice);
            ride4.setPassengers(List.of(bob));

            RideListing ride5 = new RideListing();
            ride5.setFromLocation("Murska Sobota");
            ride5.setToLocation("Kranj");
            ride5.setStartTime(LocalDateTime.now().plusDays(4).withMinute(0).withSecond(0).withNano(0));
            ride5.setPrice(8.0);
            ride5.setAllSeats(4);
            ride5.setTakenSeats(1);
            ride5.setDriver(bob);
            ride5.setPassengers(List.of(alice));

            LocationPoint point1 = new LocationPoint();
            point1.setRideListing(ride5);
            point1.setLatitude(46.0511);
            point1.setLongitude(14.5051);
            point1.setName("Start - Ljubljana");

            LocationPoint point2 = new LocationPoint();
            point2.setRideListing(ride5);
            point2.setLatitude(46.2396);
            point2.setLongitude(15.2675);
            point2.setName("Midway - Celje");

            LocationPoint point3 = new LocationPoint();
            point3.setRideListing(ride5);
            point3.setLatitude(46.5547);
            point3.setLongitude(15.6459);
            point3.setName("End - Maribor");

            rideListingRepository.save(ride5);
            locationPointRepository.saveAll(List.of(point1, point2, point3));
            ride5.setPickUpPoints(List.of(point1, point2, point3));

            rideListingRepository.saveAll(List.of(ride1, ride2, ride3, ride4));

        };
    }
}

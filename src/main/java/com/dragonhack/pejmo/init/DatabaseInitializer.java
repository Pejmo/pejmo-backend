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
            alice.setLastName("Novak");
            alice.setEmail("alenka@example.com");
            alice.setKycStatus("pending");

            User bob = new User();
            bob.setUsername("bojan");
            bob.setPassword(passwordEncoder.encode("password2"));
            bob.setFirstName("Bojan");
            bob.setLastName("Kranjc");
            bob.setEmail("bojan@example.com");
            bob.setKycStatus("validated");

            User tom = new User();
            tom.setUsername("tomaz");
            tom.setPassword(passwordEncoder.encode("password3"));
            tom.setFirstName("Tomaž");
            tom.setLastName("Mlakar");
            tom.setEmail("tomaz@example.com");
            tom.setKycStatus("validated");

            User veno = new User();
            veno.setUsername("niko");
            veno.setPassword(passwordEncoder.encode("password4"));
            veno.setFirstName("Niko");
            veno.setLastName("Potočnik");
            veno.setEmail("niko@example.com");
            veno.setKycStatus("pending");

            User veno2 = new User();
            veno2.setUsername("matija");
            veno2.setPassword(passwordEncoder.encode("password4"));
            veno2.setFirstName("Matija");
            veno2.setLastName("Gliha");
            veno2.setEmail("matija@example.com");
            veno2.setKycStatus("pending");

            User veno3 = new User();
            veno3.setUsername("rok");
            veno3.setPassword(passwordEncoder.encode("password4"));
            veno3.setFirstName("Rok");
            veno3.setLastName("Božič");
            veno3.setEmail("rok@example.com");
            veno3.setKycStatus("pending");

            userRepository.saveAll(List.of(alice, bob, tom, veno, veno2, veno3));

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

            // Create PassengerListing
            PassengerListing passengerListing2 = new PassengerListing();
            passengerListing2.setFromLocation("Ljubljana");
            passengerListing2.setToLocation("Koper");
            passengerListing2.setStartTime(LocalDateTime.now().plusDays(0).withMinute(10).withSecond(0).withNano(0));
            passengerListing2.setPrice(8.0);
            passengerListing2.setSeatsNeeded(3.0);
            passengerListing2.setPassenger(alice);

            PassengerListing passengerListing3 = new PassengerListing();
            passengerListing3.setFromLocation("Ljubljana");
            passengerListing3.setToLocation("Koper");
            passengerListing3.setStartTime(LocalDateTime.now().plusDays(0).withMinute(10).withSecond(0).withNano(0));
            passengerListing3.setPrice(6.0);
            passengerListing3.setSeatsNeeded(1.0);
            passengerListing3.setPassenger(tom);

            PassengerListing passengerListing6 = new PassengerListing();
            passengerListing6.setFromLocation("Ljubljana");
            passengerListing6.setToLocation("Koper");
            passengerListing6.setStartTime(LocalDateTime.now().plusDays(1).plusHours(3).withMinute(0).withSecond(0).withNano(0));
            passengerListing6.setPrice(9.0);
            passengerListing6.setSeatsNeeded(1.0);
            passengerListing6.setPassenger(veno);

            PassengerListing passengerListing7 = new PassengerListing();
            passengerListing7.setFromLocation("Ljubljana");
            passengerListing7.setToLocation("Koper");
            passengerListing7.setStartTime(LocalDateTime.now().plusDays(1).withMinute(0).withSecond(0).withNano(0));
            passengerListing7.setPrice(9.0);
            passengerListing7.setSeatsNeeded(2.0);
            passengerListing7.setPassenger(veno2);

            PassengerListing passengerListing8 = new PassengerListing();
            passengerListing8.setFromLocation("Ljubljana");
            passengerListing8.setToLocation("Koper");
            passengerListing8.setStartTime(LocalDateTime.now().plusDays(1).withMinute(0).withSecond(0).withNano(0));
            passengerListing8.setPrice(5.0);
            passengerListing8.setSeatsNeeded(1.0);
            passengerListing8.setPassenger(veno3);

            passengerRepository.saveAll(List.of(
                    passengerListing2,
                    passengerListing3,
                    passengerListing6,
                    passengerListing7,
                    passengerListing8
            ));

            RideListing ride1 = new RideListing();
            ride1.setFromLocation("Ljubljana");
            ride1.setToLocation("Koper");
            ride1.setStartTime(LocalDateTime.now().plusDays(0).plusHours(2).withSecond(0).withNano(0));
            ride1.setPrice(9.0);
            ride1.setAllSeats(4);
            ride1.setTakenSeats(1);
            ride1.setDriver(bob);
            ride1.setPassengers(List.of(alice));

            RideListing ride2 = new RideListing();
            ride2.setFromLocation("Ljubljana");
            ride2.setToLocation("Koper");
            ride2.setStartTime(LocalDateTime.now().plusDays(0).plusHours(2).withSecond(0).withNano(0));
            ride2.setPrice(6.0);
            ride2.setAllSeats(3);
            ride2.setTakenSeats(2);
            ride2.setDriver(alice);
            ride2.setPassengers(List.of(bob, alice));

            RideListing ride3 = new RideListing();
            ride3.setFromLocation("Koper");
            ride3.setToLocation("Ljubljana");
            ride3.setStartTime(LocalDateTime.now().plusDays(1).withMinute(0).withSecond(0).withNano(0));
            ride3.setPrice(5.0);
            ride3.setAllSeats(4);
            ride3.setTakenSeats(0);
            ride3.setDriver(bob);

            RideListing ride4 = new RideListing();
            ride4.setFromLocation("Ljubljana");
            ride4.setToLocation("Koper");
            ride4.setStartTime(LocalDateTime.now().plusDays(0).plusHours(2).withMinute(0).withSecond(0).withNano(0));
            ride4.setPrice(7.0);
            ride4.setAllSeats(2);
            ride4.setTakenSeats(1);
            ride4.setDriver(alice);
            ride4.setPassengers(List.of(bob));

            RideListing ride5 = new RideListing();
            ride5.setFromLocation("Ljubljana");
            ride5.setToLocation("Koper");
            ride5.setStartTime(LocalDateTime.now().plusDays(1).withMinute(0).withSecond(0).withNano(0));
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
            ride1.setPickUpPoints(List.of(point1, point2, point3));
            ride2.setPickUpPoints(List.of(point1, point2, point3));
            ride3.setPickUpPoints(List.of(point1, point2, point3));
            ride4.setPickUpPoints(List.of(point1, point2, point3));
            ride5.setPickUpPoints(List.of(point1, point2, point3));

            rideListingRepository.saveAll(List.of(ride1, ride2, ride3, ride4));

            RideListing ride6 = new RideListing();
            ride6.setFromLocation("Ljubljana");
            ride6.setToLocation("Maribor");
            ride6.setStartTime(LocalDateTime.now().plusDays(1).plusHours(1).withMinute(0).withSecond(0).withNano(0));
            ride6.setPrice(4.0);
            ride6.setAllSeats(3);
            ride6.setTakenSeats(0);
            ride6.setDriver(alice);

            RideListing ride7 = new RideListing();
            ride7.setFromLocation("Ljubljana");
            ride7.setToLocation("Koper");
            ride7.setStartTime(LocalDateTime.now().plusDays(1).plusHours(1).withMinute(0).withSecond(0).withNano(0));
            ride7.setPrice(5.0);
            ride7.setAllSeats(4);
            ride7.setTakenSeats(0);
            ride7.setDriver(bob);

            RideListing ride8 = new RideListing();
            ride8.setFromLocation("Koper");
            ride8.setToLocation("Ljubljana");
            ride8.setStartTime(LocalDateTime.now().plusDays(1).plusHours(0).withMinute(0).withSecond(0).withNano(0));
            ride8.setPrice(6.0);
            ride8.setAllSeats(2);
            ride8.setTakenSeats(0);
            ride8.setDriver(alice);

            RideListing ride9 = new RideListing();
            ride9.setFromLocation("Maribor");
            ride9.setToLocation("Ljubljana");
            ride9.setStartTime(LocalDateTime.now().plusDays(1).plusHours(2).withMinute(30).withSecond(0).withNano(0));
            ride9.setPrice(9.0);
            ride9.setAllSeats(4);
            ride9.setTakenSeats(0);
            ride9.setDriver(tom);

            rideListingRepository.saveAll(List.of(ride6, ride7, ride8, ride9));

            LocationPoint ride6Point1 = new LocationPoint();
            ride6Point1.setRideListing(ride6);
            ride6Point1.setLatitude(46.0569);
            ride6Point1.setLongitude(14.5058);
            ride6Point1.setName("Start - Ljubljana Center");

            LocationPoint ride6Point2 = new LocationPoint();
            ride6Point2.setRideListing(ride6);
            ride6Point2.setLatitude(46.2391);
            ride6Point2.setLongitude(14.3556);
            ride6Point2.setName("End - Kranj Center");

            locationPointRepository.saveAll(List.of(ride6Point1, ride6Point2));
            ride6.setPickUpPoints(List.of(ride6Point1, ride6Point2));

            LocationPoint ride7Point1 = new LocationPoint();
            ride7Point1.setRideListing(ride7);
            ride7Point1.setLatitude(46.2309);
            ride7Point1.setLongitude(15.2604);
            ride7Point1.setName("Start - Celje Main Station");

            LocationPoint ride7Point2 = new LocationPoint();
            ride7Point2.setRideListing(ride7);
            ride7Point2.setLatitude(46.5547);
            ride7Point2.setLongitude(15.6459);
            ride7Point2.setName("End - Maribor Main Square");

            locationPointRepository.saveAll(List.of(ride7Point1, ride7Point2));
            ride7.setPickUpPoints(List.of(ride7Point1, ride7Point2));

            LocationPoint ride8Point1 = new LocationPoint();
            ride8Point1.setRideListing(ride8);
            ride8Point1.setLatitude(45.5481);
            ride8Point1.setLongitude(13.7303);
            ride8Point1.setName("Start - Koper Center");

            LocationPoint ride8Point2 = new LocationPoint();
            ride8Point2.setRideListing(ride8);
            ride8Point2.setLatitude(45.9578);
            ride8Point2.setLongitude(13.6431);
            ride8Point2.setName("End - Nova Gorica Bus Station");

            locationPointRepository.saveAll(List.of(ride8Point1, ride8Point2));
            ride8.setPickUpPoints(List.of(ride8Point1, ride8Point2));

            LocationPoint ride1Point1 = new LocationPoint();
            ride1Point1.setRideListing(ride1);
            ride1Point1.setLatitude(46.0511);
            ride1Point1.setLongitude(14.5051);
            ride1Point1.setName("Start - Ljubljana");

            LocationPoint ride1Point2 = new LocationPoint();
            ride1Point2.setRideListing(ride1);
            ride1Point2.setLatitude(46.2396);
            ride1Point2.setLongitude(15.2675);
            ride1Point2.setName("Midway - Celje");

            LocationPoint ride1Point3 = new LocationPoint();
            ride1Point3.setRideListing(ride1);
            ride1Point3.setLatitude(46.5547);
            ride1Point3.setLongitude(15.6459);
            ride1Point3.setName("End - Maribor");

            ride1.setPickUpPoints(List.of(ride1Point1, ride1Point2, ride1Point3));
            locationPointRepository.saveAll(List.of(ride1Point1, ride1Point2, ride1Point3));

            LocationPoint ride2Point1 = new LocationPoint();
            ride2Point1.setRideListing(ride2);
            ride2Point1.setLatitude(46.2396);
            ride2Point1.setLongitude(15.2675);
            ride2Point1.setName("Start - Celje");

            LocationPoint ride2Point2 = new LocationPoint();
            ride2Point2.setRideListing(ride2);
            ride2Point2.setLatitude(46.0569);
            ride2Point2.setLongitude(14.5058);
            ride2Point2.setName("End - Kranj");

            ride2.setPickUpPoints(List.of(ride2Point1, ride2Point2));
            locationPointRepository.saveAll(List.of(ride2Point1, ride2Point2));

            LocationPoint ride3Point1 = new LocationPoint();
            ride3Point1.setRideListing(ride3);
            ride3Point1.setLatitude(45.5481);
            ride3Point1.setLongitude(13.7303);
            ride3Point1.setName("Start - Koper");

            LocationPoint ride3Point2 = new LocationPoint();
            ride3Point2.setRideListing(ride3);
            ride3Point2.setLatitude(46.0569);
            ride3Point2.setLongitude(14.5058);
            ride3Point2.setName("End - Ljubljana");

            ride3.setPickUpPoints(List.of(ride3Point1, ride3Point2));
            locationPointRepository.saveAll(List.of(ride3Point1, ride3Point2));

            LocationPoint ride4Point1 = new LocationPoint();
            ride4Point1.setRideListing(ride4);
            ride4Point1.setLatitude(46.4190);
            ride4Point1.setLongitude(15.8709);
            ride4Point1.setName("Start - Ptuj");

            LocationPoint ride4Point2 = new LocationPoint();
            ride4Point2.setRideListing(ride4);
            ride4Point2.setLatitude(46.2396);
            ride4Point2.setLongitude(15.2675);
            ride4Point2.setName("End - Celje");

            ride4.setPickUpPoints(List.of(ride4Point1, ride4Point2));
            locationPointRepository.saveAll(List.of(ride4Point1, ride4Point2));

            LocationPoint ride9Point1 = new LocationPoint();
            ride9Point1.setRideListing(ride9);
            ride9Point1.setLatitude(46.5547);
            ride9Point1.setLongitude(15.6459);
            ride9Point1.setName("Start - Maribor");

            LocationPoint ride9Point2 = new LocationPoint();
            ride9Point2.setRideListing(ride9);
            ride9Point2.setLatitude(46.2396);
            ride9Point2.setLongitude(15.2675);
            ride9Point2.setName("Midway - Celje");

            LocationPoint ride9Point3 = new LocationPoint();
            ride9Point3.setRideListing(ride9);
            ride9Point3.setLatitude(46.0569);
            ride9Point3.setLongitude(14.5058);
            ride9Point3.setName("End - Ljubljana");

            ride9.setPickUpPoints(List.of(ride9Point1, ride9Point2, ride9Point3));
            locationPointRepository.saveAll(List.of(ride9Point1, ride9Point2, ride9Point3));

            rideListingRepository.saveAll(List.of(ride1, ride2, ride3, ride4, ride9));
        };
    }
}

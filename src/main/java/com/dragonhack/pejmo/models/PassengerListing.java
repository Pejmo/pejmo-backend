package com.dragonhack.pejmo.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "passenger_listing")
public class PassengerListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fromLocation;

    @Column(nullable = false)
    private String toLocation;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double seatsNeeded;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User passenger;

    @ManyToMany
    @JoinTable(
            name = "passenger_listing_ride_offers",
            joinColumns = @JoinColumn(name = "passenger_listing_id"),
            inverseJoinColumns = @JoinColumn(name = "ride_listing_id")
    )
    private List<RideOffer> driverOffers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSeatsNeeded() {
        return seatsNeeded;
    }

    public void setSeatsNeeded(Double seatsNeeded) {
        this.seatsNeeded = seatsNeeded;
    }

    public User getPassenger() {
        return passenger;
    }

    public void setPassenger(User passenger) {
        this.passenger = passenger;
    }

    public List<RideOffer> getDriverOffers() {
        return driverOffers;
    }

    public void setDriverOffers(List<RideOffer> driverOffers) {
        this.driverOffers = driverOffers;
    }

    @Override
    public String toString() {
        return "PassengerListing{" +
                "id=" + id +
                ", fromLocation='" + fromLocation + '\'' +
                ", toLocation='" + toLocation + '\'' +
                ", startTime=" + startTime +
                ", price=" + price +
                ", seatsNeeded=" + seatsNeeded +
                ", passenger=" + passenger +
                '}';
    }
}
package com.dragonhack.pejmo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ride_offer")
public class RideOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    @ManyToOne
    @JoinColumn(name = "passenger_listing_id", nullable = false)
    private PassengerListing passengerListing;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferStatus offerStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public PassengerListing getPassengerListing() {
        return passengerListing;
    }

    public void setPassengerListing(PassengerListing passengerListing) {
        this.passengerListing = passengerListing;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    @Override
    public String toString() {
        return "RideOffer{" +
                "id=" + id +
                ", driver=" + driver.getUsername() +
                ", passengerListing=" + passengerListing.getId() +
                ", offerStatus=" + offerStatus +
                '}';
    }
}

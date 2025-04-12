package com.dragonhack.pejmo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "passenger_request")
public class PassengerRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User passenger;

    @ManyToOne
    @JoinColumn(name = "ride_listing_id", nullable = false)
    private RideListing rideListing;

    @Column(nullable = false)
    private String requestStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getPassenger() {
        return passenger;
    }

    public void setPassenger(User passenger) {
        this.passenger = passenger;
    }

    public RideListing getRideListing() {
        return rideListing;
    }

    public void setRideListing(RideListing rideListing) {
        this.rideListing = rideListing;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Override
    public String toString() {
        return "PassengerRequest{" +
                "id=" + id +
                ", passenger=" + passenger.getUsername() +
                ", rideListing=" + rideListing.getId() +
                ", requestStatus='" + requestStatus + '\'' +
                '}';
    }
}

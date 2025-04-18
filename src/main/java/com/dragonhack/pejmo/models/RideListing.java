package com.dragonhack.pejmo.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ride_listing")
public class RideListing {
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
    private Integer allSeats;

    @Column(nullable = false)
    private Integer takenSeats;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User driver;

    @ManyToMany
    @JoinTable(
            name = "ride_listing_passengers",
            joinColumns = @JoinColumn(name = "ride_listing_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> passengers;

    @OneToMany(mappedBy = "rideListing")
    private List<LocationPoint> pickUpPoints;

    public Long getId() {
        return id;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getAllSeats() {
        return allSeats;
    }

    public Integer getTakenSeats() {
        return takenSeats;
    }

    public User getDriver() {
        return driver;
    }

    public List<User> getPassengers() {
        return passengers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAllSeats(Integer allSeats) {
        this.allSeats = allSeats;
    }

    public void setTakenSeats(Integer takenSeats) {
        this.takenSeats = takenSeats;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public void setPassengers(List<User> passengers) {
        this.passengers = passengers;
    }

    public List<LocationPoint> getPickUpPoints() {
        return pickUpPoints;
    }

    public void setPickUpPoints(List<LocationPoint> pickUpPoints) {
        this.pickUpPoints = pickUpPoints;
    }


    @Override
    public String toString() {
        return "RideListing{" +
                "id=" + id +
                ", fromLocation='" + fromLocation + '\'' +
                ", toLocation='" + toLocation + '\'' +
                ", startTime=" + startTime +
                ", price=" + price +
                ", allSeats=" + allSeats +
                ", takenSeats=" + takenSeats +
                ", driver=" + driver +
                ", passengers=" + passengers +
                '}';
    }
}

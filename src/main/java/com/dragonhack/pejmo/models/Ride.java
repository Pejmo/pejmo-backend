package com.dragonhack.pejmo.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rides_table")
public class Ride {
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
    private Integer freeSeats;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public String getfromLocation() {
        return fromLocation;
    }

    public String gettoLocation() {
        return toLocation;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getFreeSeats() {
        return freeSeats;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setfromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public void settoLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setFreeSeats(Integer freeSeats) {
        this.freeSeats = freeSeats;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", fromLocation='" + fromLocation + '\'' +
                ", toLocation='" + toLocation + '\'' +
                ", startTime=" + startTime +
                ", price=" + price +
                ", user=" + user.toString() +
                '}';
    }
}

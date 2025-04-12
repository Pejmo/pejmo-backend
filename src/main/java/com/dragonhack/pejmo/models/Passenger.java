package com.dragonhack.pejmo.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "passengers_table")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fromLocation ;

    @Column(nullable = false)
    private String toLocation;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private Double minPrice;

    @Column(nullable = false)
    private Double maxPrice;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setfromLocation(String from) {
        this.fromLocation = from;
    }

    public void settoLocation(String to) {
        this.toLocation = to;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", from='" + fromLocation + '\'' +
                ", to='" + toLocation + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", user=" + user.toString() +
                '}';
    }
}

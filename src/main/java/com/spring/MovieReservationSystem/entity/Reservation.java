package com.spring.MovieReservationSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reservation")
@Getter @Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int reservationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private Customer customer;
    @OneToMany(mappedBy = "reservation")
    @JsonManagedReference
    List<ScreeningSeat> screeningSeats;

    @Column(nullable = false)
    private LocalDateTime reservationTime;
    @Column(name = "total_price")
    private float totalPrice;

}

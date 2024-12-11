package com.spring.MovieReservationSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.MovieReservationSystem.Enum.SeatStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "screening_seat")
@Getter @Setter
public class ScreeningSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "screening_id", nullable = false)
    @JsonBackReference
    private Screening screening; // Links to the Screening entity

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat; // Links to the Seat entity

    @ManyToOne
    @JoinColumn (name = "reservation_id")
    @JsonBackReference
    private Reservation reservation;

    @Column(nullable = false)
    private float price; // Price of the seat for the screening

    @Enumerated(EnumType.STRING) // Maps the enum as a string in the database
    @Column(nullable = false, length = 20)
    private SeatStatus status = SeatStatus.AVAILABLE; // Status (e.g., available, reserved)
    // Constructors
    public ScreeningSeat() {}

    public ScreeningSeat(Screening screening, Seat seat, float price, SeatStatus status) {
        this.screening = screening;
        this.seat = seat;
        this.price = price;
        this.status = status;
    }

}

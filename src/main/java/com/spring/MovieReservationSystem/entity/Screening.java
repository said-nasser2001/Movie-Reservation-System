package com.spring.MovieReservationSystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "screenings")
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screening_id")
    private int id;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;




}

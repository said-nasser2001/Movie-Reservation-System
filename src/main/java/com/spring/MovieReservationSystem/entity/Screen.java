package com.spring.MovieReservationSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "screens")
@Getter @Setter
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_id")
    private int id;
    @Column(name = "screen_number")
    private int ScreenNumber;
    @Column(name = "seating_capacity")
    private int seatingCapacity;
    @ManyToOne
    @JoinColumn(name = "theater_id")
    @JsonManagedReference
    private Theater theater;
    @OneToMany(mappedBy = "screen")
    @JsonIgnore
    List<Screening> screenings;
}

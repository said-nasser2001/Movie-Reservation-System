package com.spring.MovieReservationSystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theaters")
@Getter @Setter
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    private int id;
    @Column(name = "theater_name")
    private String theaterName;
    private String location;
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<Screen> screens= new ArrayList<>();
    public void addScreen(Screen screen) {
        screens.add(screen);
        screen.setTheater(this);
    }

}

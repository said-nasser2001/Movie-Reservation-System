package com.spring.MovieReservationSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movies")
@Getter @Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int id;
    private String title;
    private int duration;
    private String genre;
    private String description;
    @Column(name = "release_date")
    private Date releaseDate;
    private String rating;
    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    List<Screening> screenings;

}

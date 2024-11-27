package com.spring.MovieReservationSystem.Repository;

import com.spring.MovieReservationSystem.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
}

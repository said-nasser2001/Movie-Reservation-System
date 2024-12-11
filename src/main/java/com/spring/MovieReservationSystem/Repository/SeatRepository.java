package com.spring.MovieReservationSystem.Repository;

import com.spring.MovieReservationSystem.entity.Screen;
import com.spring.MovieReservationSystem.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Integer> {
List<Seat> findByScreen (Screen screen);
}

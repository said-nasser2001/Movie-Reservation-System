package com.spring.MovieReservationSystem.Repository;

import com.spring.MovieReservationSystem.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    List<Reservation> findByCustomerId(int customerId);

}

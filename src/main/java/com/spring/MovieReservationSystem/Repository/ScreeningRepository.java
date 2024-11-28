package com.spring.MovieReservationSystem.Repository;

import com.spring.MovieReservationSystem.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<Screening,Integer> {
}

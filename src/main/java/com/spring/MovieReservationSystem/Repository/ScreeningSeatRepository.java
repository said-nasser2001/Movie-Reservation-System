package com.spring.MovieReservationSystem.Repository;

import com.spring.MovieReservationSystem.entity.Screening;
import com.spring.MovieReservationSystem.entity.ScreeningSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreeningSeatRepository extends JpaRepository<ScreeningSeat,Integer> {
    List<ScreeningSeat> findByScreening(Screening screening);
}

package com.spring.MovieReservationSystem.Repository;

import com.spring.MovieReservationSystem.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening,Integer> {
    @Query("SELECT s FROM Screening s WHERE FUNCTION('DATE', s.startTime) = :date")
    List<Screening> findByDate(@Param("date") LocalDate date);
    @Query("SELECT s FROM Screening s WHERE s.startTime >= :today ORDER BY s.startTime ASC")
    List<Screening> findScreeningsFromToday(@Param("today") LocalDateTime today);




}

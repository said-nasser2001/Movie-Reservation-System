package com.spring.MovieReservationSystem.Repository;

import com.spring.MovieReservationSystem.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Integer> {
}

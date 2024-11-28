package com.spring.MovieReservationSystem.Repository;

import com.spring.MovieReservationSystem.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenRepository extends JpaRepository<Screen,Integer> {
}

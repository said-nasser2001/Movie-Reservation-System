package com.spring.MovieReservationSystem.Repository;

import com.spring.MovieReservationSystem.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Integer> {
}

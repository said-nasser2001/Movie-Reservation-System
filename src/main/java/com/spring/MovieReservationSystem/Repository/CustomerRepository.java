package com.spring.MovieReservationSystem.Repository;

import com.spring.MovieReservationSystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Customer,Integer> {

    Optional<Customer> findByEmail(String email);

}

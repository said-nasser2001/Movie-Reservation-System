package com.spring.MovieReservationSystem;

import com.spring.MovieReservationSystem.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieReservationSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(MovieReservationSystemApplication.class, args);

	}

}

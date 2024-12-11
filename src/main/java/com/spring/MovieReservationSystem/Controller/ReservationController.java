package com.spring.MovieReservationSystem.Controller;

import com.spring.MovieReservationSystem.entity.Reservation;
import com.spring.MovieReservationSystem.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;


    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody List<Integer> screeningSeatIds) {
        Reservation reservation = reservationService.createReservation(screeningSeatIds);
        return ResponseEntity.ok(reservation);
    }
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.noContent().build();
    }
}

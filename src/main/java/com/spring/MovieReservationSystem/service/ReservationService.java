package com.spring.MovieReservationSystem.service;

import com.spring.MovieReservationSystem.Enum.SeatStatus;
import com.spring.MovieReservationSystem.Repository.CustomerRepository;
import com.spring.MovieReservationSystem.Repository.ReservationRepository;
import com.spring.MovieReservationSystem.Repository.ScreeningSeatRepository;
import com.spring.MovieReservationSystem.entity.Customer;
import com.spring.MovieReservationSystem.entity.Reservation;
import com.spring.MovieReservationSystem.entity.ScreeningSeat;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationService {
    @Autowired
   private final ReservationRepository reservationRepository;
    @Autowired
    private final ScreeningSeatRepository screeningSeatRepository;
    @Autowired
    private final CustomerRepository customerRepository;


    private String getLoggedInUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString(); // Fallback if principal is just a string
        }
    }


    @Transactional
    public Reservation createReservation(List<Integer> screeningSeatIds) {
        // Validate and fetch the screening seats
        List<ScreeningSeat> screeningSeats = screeningSeatRepository.findAllById(screeningSeatIds);
        if (screeningSeats.isEmpty() || screeningSeats.size() != screeningSeatIds.size()) {
            throw new IllegalArgumentException("Invalid screening seat IDs provided.");
        }

        // Ensure all seats are available
        for (ScreeningSeat screeningSeat : screeningSeats) {
            if (screeningSeat.getStatus() != SeatStatus.AVAILABLE) {
                throw new IllegalStateException("One or more seats are not available.");
            }
        }

        // Calculate the total price
        float totalPrice =(float) (screeningSeats.stream()
                .mapToDouble(ScreeningSeat::getPrice)
                .sum());
        String username = getLoggedInUsername();

        Customer customer = customerRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + username));
        // Create a new reservation
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setReservationTime(LocalDateTime.now());
        reservation.setTotalPrice(totalPrice);
        reservation.setScreeningSeats(screeningSeats);

        // Save the reservation
        reservation = reservationRepository.save(reservation);

        // Update the status of the reserved seats
        for (ScreeningSeat screeningSeat : screeningSeats) {
            screeningSeat.setStatus(SeatStatus.RESERVED);
            screeningSeat.setReservation(reservation);
            screeningSeatRepository.save(screeningSeat);
        }

        return reservation;
    }
    @Transactional
    public void cancelReservation(int reservationId) {
        // Fetch the reservation
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with ID: " + reservationId));

        // Release the reserved seats
        for (ScreeningSeat screeningSeat : reservation.getScreeningSeats()) {
            screeningSeat.setStatus(SeatStatus.AVAILABLE);
            screeningSeat.setReservation(null);
            screeningSeatRepository.save(screeningSeat);
        }

        // Delete the reservation
        reservationRepository.delete(reservation);
    }


    @Transactional
    public Reservation getReservationById(int reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with ID: " + reservationId));
    }
    @Transactional
    public List<Reservation> getAllReservationsForUser ()
    {
        String username = getLoggedInUsername();

        Customer customer = customerRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + username));
        return reservationRepository.findByCustomerId(customer.getId());

    }

    public List<Reservation> getAllReservationsForUser (int customerId)
    {

        return reservationRepository.findByCustomerId(customerId);

    }

}

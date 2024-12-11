package com.spring.MovieReservationSystem.service;

import com.spring.MovieReservationSystem.Repository.ScreenRepository;
import com.spring.MovieReservationSystem.Repository.SeatRepository;
import com.spring.MovieReservationSystem.entity.Screen;
import com.spring.MovieReservationSystem.entity.Seat;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ScreenRepository screenRepository;
    @Transactional
    public void generateSeatsForAllScreens() {
        // Retrieve all screens
        List<Screen> screens = screenRepository.findAll();
        for (Screen screen : screens) {
            generateSeatsForScreen(screen.getId());
            System.out.println("Seats for table " + screen.getId()+" had been generated");
        }
    }

    @Transactional
    public void  generateSeatsForScreen(int screenId) {
        Screen screen = screenRepository.findById(screenId)
                .orElseThrow(() -> new IllegalArgumentException("Screen not found with ID: " + screenId));

        // Check if seats already exist for this screen
        if (!seatRepository.findByScreen(screen).isEmpty()) {
            throw new IllegalStateException("Seats already exist for this screen!");
        }

        int seatingCapacity = screen.getSeatingCapacity();
        for (int i = 1; i <= seatingCapacity; i++) {
            String seatNumber = "S" + screen.getScreenNumber() + "-" + i; // Example: "S1-1", "S1-2", etc.
            Seat seat = new Seat();
            seat.setScreen(screen);
            seat.setSeatNumber(seatNumber);
            seatRepository.save(seat);
        }

    }
    public Seat getSeatById(int id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Screen not found with id " + id));
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public void deleteSeat(int id) {
        seatRepository.deleteById(id);
    }
    public Screen updateSeat(int id, Screen updatedSeat) {
        updatedSeat.setId(id);
        return screenRepository.save(updatedSeat);
    }

}

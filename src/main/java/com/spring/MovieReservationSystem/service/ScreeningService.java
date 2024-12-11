package com.spring.MovieReservationSystem.service;

import com.spring.MovieReservationSystem.Enum.SeatStatus;
import com.spring.MovieReservationSystem.Repository.*;
import com.spring.MovieReservationSystem.dto.ScreeningRequest;
import com.spring.MovieReservationSystem.entity.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreeningService {
    private final ScreeningSeatRepository screeningSeatRepository;

    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;
    private final SeatRepository  seatRepository;
    @Transactional
    public void generateScreeningSeatsForAllScreening()
    {
        List<Screening> screenings =screeningRepository.findAll();
        for (Screening screening:screenings)
        {
            if (!screeningSeatRepository.findByScreening(screening).isEmpty()) {
                System.out.println("Seats already exist for this screening!");
                continue;
            }
            generateScreeningSeats(screening);
            System.out.println("screening seats for screening" +screening.getId()+" had been created");
        }
    }
    @Transactional
    public void generateScreeningSeats(Screening screening) {

        // Retrieve the seats associated with the screen
        List<Seat> seats = seatRepository.findByScreen(screening.getScreen());

        // Iterate over the seats and create ScreeningSeat entities for this screening
        for (Seat seat : seats) {
            ScreeningSeat screeningSeat = new ScreeningSeat();
            screeningSeat.setScreening(screening);
            screeningSeat.setSeat(seat);
            screeningSeat.setStatus(SeatStatus.AVAILABLE);
            screeningSeat.setPrice(50);
            screeningSeatRepository.save(screeningSeat);
        }
    }

    public Screening AddScreening(ScreeningRequest screeningRequest) {
        Movie movie = movieRepository.findById(screeningRequest.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("Movie with ID " + screeningRequest.getMovieId() + " not found."));
        Screen screen = screenRepository.findById(screeningRequest.getScreenId())
                .orElseThrow(() -> new IllegalArgumentException("Screen with ID " + screeningRequest.getScreenId() + " not found."));

        // Create and Save the Screening
        Screening screening = new Screening();
        screening.setMovie(movie);
        screening.setScreen(screen);
        screening.setStartTime(screeningRequest.getStartTime());
        screening.setEndTime(screeningRequest.getEndTime());
        generateScreeningSeats(screening);
        return screeningRepository.save(screening);
    }
    public List<Screening> addScreenings(List<ScreeningRequest> screeningRequests) {
        List<Screening> screenings = new ArrayList<>();

        for (ScreeningRequest request : screeningRequests) {
           screenings.add(AddScreening(request));
        }
        return screenings;
    }

    public Screening getScreeningById(int id) {
        return screeningRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Screening not found with id " + id));
    }
    public List<Screening> getScreeningsByDate(LocalDate date) {
        return screeningRepository.findByDate(date);
    }

    public List<Screening> getScreeningsFromToday() {
        LocalDateTime localDateTime = LocalDate.now().atStartOfDay();
        return screeningRepository.findScreeningsFromToday(localDateTime);
    }


    public List<Screening> getAllScreenings() {
        LocalDateTime date = LocalDateTime.now();
        return screeningRepository.findScreeningsFromToday(date);
    }
    public Screening updateScreening(int id, Screening updatedScreening) {
        updatedScreening.setId(id);
        return screeningRepository.save(updatedScreening);
    }

    public void deleteScreening(int id) {
        screeningRepository.deleteById(id);
    }

}

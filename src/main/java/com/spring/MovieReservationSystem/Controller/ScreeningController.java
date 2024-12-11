package com.spring.MovieReservationSystem.Controller;

import com.spring.MovieReservationSystem.dto.ScreeningRequest;
import com.spring.MovieReservationSystem.entity.Screening;
import com.spring.MovieReservationSystem.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/screening")
public class ScreeningController {
    private final ScreeningService screeningService;

    @Autowired
    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    // Create a new screening
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Screening> createScreening(@RequestBody ScreeningRequest screening) {
        Screening createdScreening = screeningService.AddScreening(screening);
        screeningService.generateScreeningSeats(createdScreening);
        return new ResponseEntity<>(createdScreening, HttpStatus.CREATED);
    }
    @PostMapping("/add-screenings")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Screening>> saveMultipleScreenings(@RequestBody List<ScreeningRequest> screeningRequests) {
        List<Screening> screenings = screeningService.addScreenings(screeningRequests);
        return new ResponseEntity<>(screenings, HttpStatus.CREATED);
    }
    @GetMapping("/generate-screening-seats")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<String> generateSeats()
    {
        screeningService.generateScreeningSeatsForAllScreening();
        return ResponseEntity.ok("Seats generated successfully for all Screenings.");
    }
    // Get all screenings
    @GetMapping
    public ResponseEntity<List<Screening>> getAllScreenings() {
        List<Screening> screenings = screeningService.getScreeningsFromToday();
        return new ResponseEntity<>(screenings, HttpStatus.OK);
    }


    // Get a screening by ID
    @GetMapping("/{id}")
    public ResponseEntity<Screening> getScreeningById(@PathVariable int id) {
        Screening screening = screeningService.getScreeningById(id);
        return new ResponseEntity<>(screening, HttpStatus.OK);
    }
    @GetMapping("/filterByDate")
    public ResponseEntity<List<Screening>> getScreeningByDate(@RequestParam("date")
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Screening> screening = screeningService.getScreeningsByDate(date);
        return new ResponseEntity<>(screening, HttpStatus.OK);
    }

    // Update a screening
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Screening> updateScreening(@PathVariable int id, @RequestBody Screening updatedScreening) {
        Screening updated = screeningService.updateScreening(id, updatedScreening);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Delete a screening
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Void> deleteScreening(@PathVariable int id) {
        screeningService.deleteScreening(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

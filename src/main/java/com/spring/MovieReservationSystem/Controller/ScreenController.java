package com.spring.MovieReservationSystem.Controller;

import com.spring.MovieReservationSystem.entity.Screen;
import com.spring.MovieReservationSystem.service.ScreenService;
import com.spring.MovieReservationSystem.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/screens")
public class ScreenController {
    private final ScreenService screenService;
    private final SeatService seatService;
    @Autowired
    public ScreenController(ScreenService screenService,SeatService seatService) {
        this.screenService = screenService;
        this.seatService =seatService;
    }

    @GetMapping("/generate-seats")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> generateSeats()
    {
        seatService.generateSeatsForAllScreens();
        return ResponseEntity.ok("Seats generated successfully for all screens.");
    }
    @PostMapping("/{theaterId}")
    public ResponseEntity<Screen> createScreen(@RequestBody Screen screen, @PathVariable int theaterId) {
        Screen savedScreen = screenService.saveScreen(screen, theaterId);
        seatService.generateSeatsForScreen(savedScreen.getId());
        return new ResponseEntity<>(savedScreen, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Screen> getScreenById(@PathVariable int id) {
        Screen screen = screenService.getScreenById(id);
        return ResponseEntity.ok(screen);
    }

    @GetMapping
    public ResponseEntity<List<Screen>> getAllScreens() {
        List<Screen> screens = screenService.getAllScreens();
        return ResponseEntity.ok(screens);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Screen> updateScreen(@PathVariable int id, @RequestBody Screen updatedScreen) {
        Screen updated = screenService.updateScreen(id, updatedScreen);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteScreen(@PathVariable int id) {
        screenService.deleteScreen(id);
        return ResponseEntity.noContent().build();
    }
}


package com.spring.MovieReservationSystem.Controller;


import com.spring.MovieReservationSystem.entity.Theater;
import com.spring.MovieReservationSystem.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/theater")
public class TheaterController {
    private  final TheaterService theaterService;

    @GetMapping
    public List<Theater> getAllTheaters ()
    {
        return theaterService.getTheaters();
    }
    @GetMapping("/{id}")
    public Theater getTheater(@PathVariable int id)
    {
        return theaterService.getTheaterById(id);
    }
    @PostMapping
    public Theater addTheater (@RequestBody Theater theater)
    {
        return theaterService.saveTheater(theater);
    }
    @PostMapping("/add-theaters")
    public ResponseEntity<String> addTheaters(@RequestBody List<Theater> theaters) {
        theaterService.saveAllTheaters(theaters);
        return ResponseEntity.status(HttpStatus.CREATED).body("Theaters added successfully");
    }
    @PutMapping("/{id}")
    public Theater updateTheaters(@RequestBody Theater theater,@PathVariable int id)
    {
        theater.setId(id);
        return theaterService.updateTheater(theater,id);
    }
    @DeleteMapping("/{id}")
    public void deleteTheater(@PathVariable int id)
    {
        theaterService.deleteTheater(id);
    }
}






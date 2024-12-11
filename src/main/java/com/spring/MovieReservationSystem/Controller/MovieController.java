package com.spring.MovieReservationSystem.Controller;

import com.spring.MovieReservationSystem.entity.Movie;
import com.spring.MovieReservationSystem.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private final MovieService movieService;
    @GetMapping
    public List<Movie> getAllMovies ()
    {
        return movieService.getMovies();
    }
    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable int id)
    {
        return movieService.getMovieById(id);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Movie addMovie (@RequestBody Movie movie)
    {
        return movieService.saveMovie(movie);
    }
    @PostMapping("/add-movies")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addMovies(@RequestBody List<Movie> movies) {
        movieService.saveAllMovies(movies);
        return ResponseEntity.status(HttpStatus.CREATED).body("Movies added successfully");
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Movie updateMovie(@RequestBody Movie movie,@PathVariable int id)
    {
        movie.setId(id);
        return movieService.updateMovie(movie,id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteMovie(@PathVariable int id)
    {
        movieService.deleteMovie(id);
    }
}

package com.spring.MovieReservationSystem.service;

import com.spring.MovieReservationSystem.Repository.MovieRepository;
import com.spring.MovieReservationSystem.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    @Autowired
    private final MovieRepository movieRepository;
    public List<Movie> getMovies ()
    {
        return movieRepository.findAll();
    }
    public Movie getMovieById(int id){
        Optional<Movie> res = movieRepository.findById(id);
        if (res.isEmpty()) {
            throw new RuntimeException("Did not find employee id - " + id);
        }
        return res.get();

    }
    public Movie saveMovie(Movie movie)
    {
        return movieRepository.save(movie);
    }
    public void saveAllMovies(List<Movie>movies)
    {
        movieRepository.saveAll(movies);
    }
    public Movie updateMovie(Movie movie ,int id)
    {
        movie.setId(id);
        return movieRepository.save(movie);
    }
    public void deleteMovie(int id)
    {
        movieRepository.deleteById(id);
    }



}

package com.spring.MovieReservationSystem.service;

import com.spring.MovieReservationSystem.Repository.ScreenRepository;
import com.spring.MovieReservationSystem.Repository.TheaterRepository;
import com.spring.MovieReservationSystem.entity.Screen;
import com.spring.MovieReservationSystem.entity.Theater;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private ScreenRepository screenRepository;


    public List<Theater> getTheaters ()
    {
        return theaterRepository.findAll();
    }
    public Theater getTheaterById(int id){
        Optional<Theater> res = theaterRepository.findById(id);
        if (res.isEmpty()) {
            throw new RuntimeException("Did not find theater id - " + id);
        }
        return res.get();

    }
    @Transactional
    public Theater saveTheater(Theater theater)

    {
        for (Screen screen : theater.getScreens()) {
            screen.setTheater(theater);  // Ensure the 'theater' field is set
        }

        // Save the theater; CascadeType.ALL will ensure screens are saved as well
        return theaterRepository.save(theater);
    }
    public void saveAllTheaters(List<Theater>theaters)
    {for (Theater theater : theaters) {
        for (Screen screen : theater.getScreens()) {
            screen.setTheater(theater);  // Ensure the 'theater' field is set
        }
        theaterRepository.save(theater);
    }
    }
    public Theater updateTheater(Theater theater ,int id)
    {
        theater.setId(id);
        return theaterRepository.save(theater);
    }

    public void deleteTheater(int id)
    {
        theaterRepository.deleteById(id);
    }


}

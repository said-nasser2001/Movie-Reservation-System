package com.spring.MovieReservationSystem.service;

import com.spring.MovieReservationSystem.Repository.ScreenRepository;
import com.spring.MovieReservationSystem.Repository.TheaterRepository;
import com.spring.MovieReservationSystem.entity.Screen;
import com.spring.MovieReservationSystem.entity.Theater;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenService {
    private ScreenRepository screenRepository;
    private TheaterRepository theaterRepository;

    public Screen saveScreen(Screen screen,int theaterId) {
        Theater theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new RuntimeException("Theater not found with id " + theaterId));
        screen.setTheater(theater);
        return screenRepository.save(screen);
    }


    public Screen getScreenById(int id) {
        return screenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Screen not found with id " + id));
    }

    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    public void deleteScreen(int id) {
        screenRepository.deleteById(id);
    }
    public Screen updateScreen(int id, Screen updatedScreen) {
        updatedScreen.setId(id);
        return screenRepository.save(updatedScreen);
    }

}

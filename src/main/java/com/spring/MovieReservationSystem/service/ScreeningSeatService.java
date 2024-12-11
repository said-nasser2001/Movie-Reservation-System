package com.spring.MovieReservationSystem.service;

import com.spring.MovieReservationSystem.Enum.SeatStatus;
import com.spring.MovieReservationSystem.Repository.ScreeningRepository;
import com.spring.MovieReservationSystem.Repository.ScreeningSeatRepository;
import com.spring.MovieReservationSystem.Repository.SeatRepository;
import com.spring.MovieReservationSystem.entity.Screen;
import com.spring.MovieReservationSystem.entity.Screening;
import com.spring.MovieReservationSystem.entity.ScreeningSeat;
import com.spring.MovieReservationSystem.entity.Seat;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RequiredArgsConstructor
public class ScreeningSeatService {
    @Autowired
    private final ScreeningSeatRepository screeningSeatRepository;

    @Autowired
    private final ScreeningRepository screeningRepository;
    @Autowired
    private  final SeatRepository seatRepository;








}

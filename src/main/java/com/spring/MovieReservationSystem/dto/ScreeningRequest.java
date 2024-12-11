package com.spring.MovieReservationSystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter @Setter
public class ScreeningRequest {
    private int movieId;
    private int screenId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


}

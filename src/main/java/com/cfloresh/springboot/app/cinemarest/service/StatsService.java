package com.cfloresh.springboot.app.cinemarest.service;

import com.cfloresh.springboot.app.cinemarest.model.CinemaRoom;
import com.cfloresh.springboot.app.cinemarest.model.Stats;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    private final Stats cinemaStats;
    private final CinemaService cinemaService;

    public StatsService(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
        cinemaStats = new Stats(0,
                this.cinemaService.getAvaiableSeats(),
                this.cinemaService.getSoldSeats());
    }

    public void increaseIncome(int value) {
        cinemaStats.setIncome(cinemaStats.getIncome() + value);
    }

    public void decreaseIncome(int value) {
        cinemaStats.setIncome(cinemaStats.getIncome() - value);
    }

    public void updateSeats() {
        cinemaStats.setAvailable(cinemaService.getAvaiableSeats());
        cinemaStats.setPurchased(cinemaService.getSoldSeats());
    }

    public Stats getStats() {
        return cinemaStats;
    }
}

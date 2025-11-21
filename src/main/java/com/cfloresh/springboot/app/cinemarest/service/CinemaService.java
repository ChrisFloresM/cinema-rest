package com.cfloresh.springboot.app.cinemarest.service;

import com.cfloresh.springboot.app.cinemarest.config.CinemaProperties;
import com.cfloresh.springboot.app.cinemarest.model.CinemaRoom;
import com.cfloresh.springboot.app.cinemarest.model.PurchaseRequest;
import com.cfloresh.springboot.app.cinemarest.model.Seat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {
    private final CinemaRoom cinemaRoom;
    private final CinemaProperties properties;

    public CinemaService(CinemaProperties properties) {
        this.properties = properties;
        this.cinemaRoom = new CinemaRoom(properties.getRows(), properties.getColumns());
    }

    public boolean isOutOfBounds(PurchaseRequest request) {
        return request.getRow() > properties.getRows()
                || request.getColumn() > properties.getColumns()
                || request.getRow() < 1
                || request.getColumn() < 1;
    }

    public Optional<Seat> purchaseTicket(PurchaseRequest request) {
        Optional<Seat> seatOptional = cinemaRoom.getSeats().stream()
                .filter(seat -> seat.getRow() == request.getRow() && seat.getColumn() == request.getColumn())
                .findFirst();

        if (seatOptional.isEmpty()) {
            return Optional.empty();
        }

        Seat purchasedSeat = seatOptional.get();

        if (purchasedSeat.isBooked()) {
            return Optional.empty();
        }

        purchasedSeat.setBooked(true);
        return Optional.of(purchasedSeat);
    }

    public CinemaRoom getCinemaRoom() {
        return this.cinemaRoom;
    }
}

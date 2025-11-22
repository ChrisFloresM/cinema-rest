package com.cfloresh.springboot.app.cinemarest.service;

import com.cfloresh.springboot.app.cinemarest.config.CinemaProperties;
import com.cfloresh.springboot.app.cinemarest.exception.PurchaseOutOfBoundException;
import com.cfloresh.springboot.app.cinemarest.exception.SeatNotAvaiableException;
import com.cfloresh.springboot.app.cinemarest.model.*;
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

    public Seat reserveSeat(PurchaseRequest request) {
        Seat reservedSeat = findSeat(request.getRow(), request.getColumn());

        if (reservedSeat.isBooked()) {
            throw new SeatNotAvaiableException("The ticket has been already purchased!");
        }

        reservedSeat.setBooked(true);
        return reservedSeat;
    }

    public void releaseSeat(Ticket ticket) {
        Seat seat = findSeat(ticket.getRow(), ticket.getColumn());
        seat.setBooked(false);
    }

    private Seat findSeat(int row, int column) {
        return cinemaRoom.getSeats().stream()
                .filter(s -> s.getRow() == row && s.getColumn() == column)
                .findFirst().orElseThrow(() -> new PurchaseOutOfBoundException("The number of a row or a " +
                        "column is out of bounds!"));
    }

    public CinemaRoom getCinemaRoom() {
        return this.cinemaRoom;
    }
}

package com.cfloresh.springboot.app.cinemarest.model;

import java.util.ArrayList;
import java.util.List;

public class CinemaRoom {
    private int rows;
    private int columns;
    private List<Seat> seats;

    public CinemaRoom(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = initializeSeats();
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    private List<Seat> initializeSeats(){
        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i<rows; i++){
            for (int j = 0; j<columns; j++){
                Seat seat = new Seat(i + 1, j + 1);
                seats.add(seat);
            }
        }

        return seats;
    }
}

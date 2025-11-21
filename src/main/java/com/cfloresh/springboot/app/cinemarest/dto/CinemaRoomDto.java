package com.cfloresh.springboot.app.cinemarest.dto;

import com.cfloresh.springboot.app.cinemarest.model.Seat;

import java.util.List;

public class CinemaRoomDto {
    /* Note: DTO is the same as model. Used here just to practice and know it can be used */

    private int rows;
    private int columns;
    private List<SeatDto> seats;

    public CinemaRoomDto(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
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

    public List<SeatDto> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDto> seats) {
        this.seats = seats;
    }
}

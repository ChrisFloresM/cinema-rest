package com.cfloresh.springboot.app.cinemarest.model;

public class Seat {
    private static final int ROW_THRESHOLD = 4;
    private static final int EXPENSIVE_TICKET_VALUE = 10;
    private static final int CHEAP_TICKET_VALUE = 8;

    private static int calculatePrice(int row) {
        return row <= ROW_THRESHOLD ? EXPENSIVE_TICKET_VALUE : CHEAP_TICKET_VALUE;
    }

    private int row;
    private int column;
    private int price;
    private boolean isBooked;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = calculatePrice(row);
        this.isBooked = false;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}

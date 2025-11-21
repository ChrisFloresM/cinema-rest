package com.cfloresh.springboot.app.cinemarest.dto;

public class SeatDto {
    /* Note: DTO is the same as model. Used here just to practice and know it can be used */
    private int row;
    private int column;
    private int price;

    public SeatDto(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
}

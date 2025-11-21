package com.cfloresh.springboot.app.cinemarest.model;

public class PurchaseRequest {
    private int row;
    private int column;

    public PurchaseRequest() {
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

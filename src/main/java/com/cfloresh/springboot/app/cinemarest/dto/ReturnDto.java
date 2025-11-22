package com.cfloresh.springboot.app.cinemarest.dto;

import com.cfloresh.springboot.app.cinemarest.model.Ticket;

public class ReturnDto {
    private Ticket ticket;

    public ReturnDto(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}

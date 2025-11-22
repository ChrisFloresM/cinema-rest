package com.cfloresh.springboot.app.cinemarest.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"token", "ticket"})
public class Purchase {
    private String token;
    private Ticket ticket;

    public Purchase(Ticket ticket) {
        this.token = UUID.randomUUID().toString();
        this.ticket = ticket;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}

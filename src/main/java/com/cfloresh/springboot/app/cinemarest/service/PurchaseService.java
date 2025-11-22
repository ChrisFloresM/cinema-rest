package com.cfloresh.springboot.app.cinemarest.service;

import com.cfloresh.springboot.app.cinemarest.exception.PurchaseNotFoundException;
import com.cfloresh.springboot.app.cinemarest.model.*;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PurchaseService {
    private final Map<String, Purchase> purchases = new ConcurrentHashMap<>();
    private final CinemaService cinemaService;

    public PurchaseService(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    public Purchase purchaseSeat(PurchaseRequest request) {
        Seat reservedSeat = cinemaService.reserveSeat(request);

        Purchase purchase = new Purchase(new Ticket(reservedSeat.getRow(), reservedSeat.getColumn(),
                reservedSeat.getPrice()));

        purchases.put(purchase.getToken(), purchase);
        return purchase;
    }

    public Ticket returnPurchase(ReturnRequest request) {
        Purchase purchase = purchases.get(request.getToken());

        if (purchase == null) {
            throw new PurchaseNotFoundException("Wrong token!");
        }

        Ticket purchaseTicket = purchase.getTicket();
        cinemaService.releaseSeat(purchaseTicket);
        purchases.remove(request.getToken());

        return purchaseTicket;
    }
}

package com.cfloresh.springboot.app.cinemarest.controller;

import com.cfloresh.springboot.app.cinemarest.dto.CinemaRoomDto;
import com.cfloresh.springboot.app.cinemarest.dto.ReturnDto;
import com.cfloresh.springboot.app.cinemarest.exception.WrongPasswordException;
import com.cfloresh.springboot.app.cinemarest.mapper.CinemaMapper;
import com.cfloresh.springboot.app.cinemarest.model.*;
import com.cfloresh.springboot.app.cinemarest.error.ErrorMessage;
import com.cfloresh.springboot.app.cinemarest.service.CinemaService;
import com.cfloresh.springboot.app.cinemarest.service.PurchaseService;
import com.cfloresh.springboot.app.cinemarest.service.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class CinemaController {
    /* Just a practice excercise. In RW, this obviously shouldn't be here */
    private static final String CORRECT_PASSWORD = "super_secret";

    private final CinemaService cinemaService;
    private final PurchaseService purchaseService;
    private final StatsService statsService;
    private final CinemaMapper cinemaMapper;

    /* DI using constructor */
    public CinemaController(CinemaService cinemaService, CinemaMapper cinemaMapper,
                            PurchaseService purchaseService, StatsService statsService) {
        this.cinemaService = cinemaService;
        this.cinemaMapper = cinemaMapper;
        this.purchaseService = purchaseService;
        this.statsService = statsService;
    }

    /* Seats Endpoint */
    @GetMapping("/seats")
    public ResponseEntity<CinemaRoomDto> getSeats() {
        CinemaRoom room = cinemaService.getCinemaRoom();
        CinemaRoomDto response = cinemaMapper.toResponse(room);

        return ResponseEntity.ok(response);
    }

    /* Purchase and return endpoints */
    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody PurchaseRequest request) {
        /* Create out of bounds response */
        if (cinemaService.isOutOfBounds(request)) {
            return ResponseEntity.badRequest().body(new ErrorMessage("The number of a row or a " +
                    "column is out of bounds!"));
        }

        Purchase purchase = purchaseService.purchaseSeat(request);
        return ResponseEntity.ok(purchase);
    }

    @PostMapping("/return")
    public ResponseEntity<ReturnDto> returnPurchase(@RequestBody ReturnRequest request) {
        Ticket ticket =  purchaseService.returnPurchase(request);
        return ResponseEntity.ok(new ReturnDto(ticket));
    }

    /* Stats endpoint */
    @GetMapping("/stats")
    public ResponseEntity<Stats> getStats(@RequestParam(required = false) String password) {
        if (password == null || !password.equals(CORRECT_PASSWORD)) {
            throw new WrongPasswordException("The password is wrong!");
        }

        return ResponseEntity.ok(statsService.getStats());
    }
}

package com.cfloresh.springboot.app.cinemarest.controller;

import com.cfloresh.springboot.app.cinemarest.dto.CinemaRoomDto;
import com.cfloresh.springboot.app.cinemarest.dto.ReturnDto;
import com.cfloresh.springboot.app.cinemarest.mapper.CinemaMapper;
import com.cfloresh.springboot.app.cinemarest.model.*;
import com.cfloresh.springboot.app.cinemarest.error.ErrorMessage;
import com.cfloresh.springboot.app.cinemarest.service.CinemaService;
import com.cfloresh.springboot.app.cinemarest.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
public class CinemaController {
    private final CinemaService cinemaService;
    private final PurchaseService purchaseService;
    private final CinemaMapper cinemaMapper;

    /* DI using constructor */
    public CinemaController(CinemaService cinemaService, CinemaMapper cinemaMapper, PurchaseService purchaseService) {
        this.cinemaService = cinemaService;
        this.cinemaMapper = cinemaMapper;
        this.purchaseService = purchaseService;
    }

    @GetMapping("/seats")
    public ResponseEntity<CinemaRoomDto> getSeats() {
        CinemaRoom room = cinemaService.getCinemaRoom();
        CinemaRoomDto response = cinemaMapper.toResponse(room);

        return ResponseEntity.ok(response);
    }

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
}

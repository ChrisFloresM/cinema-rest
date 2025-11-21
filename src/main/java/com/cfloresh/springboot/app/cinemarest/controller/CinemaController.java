package com.cfloresh.springboot.app.cinemarest.controller;

import com.cfloresh.springboot.app.cinemarest.dto.CinemaRoomDto;
import com.cfloresh.springboot.app.cinemarest.mapper.CinemaMapper;
import com.cfloresh.springboot.app.cinemarest.model.CinemaRoom;
import com.cfloresh.springboot.app.cinemarest.model.PurchaseRequest;
import com.cfloresh.springboot.app.cinemarest.model.RequestError;
import com.cfloresh.springboot.app.cinemarest.model.Seat;
import com.cfloresh.springboot.app.cinemarest.service.CinemaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CinemaController {
    private final CinemaService cinemaService;
    private final CinemaMapper cinemaMapper;

    /* DI using constructor */
    public CinemaController(CinemaService cinemaService, CinemaMapper cinemaMapper) {
        this.cinemaService = cinemaService;
        this.cinemaMapper = cinemaMapper;
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
            return ResponseEntity.badRequest().body(new RequestError("The number of a row or a " +
                    "column is out of bounds!"));
        }

        Optional<Seat> optionalSeat = cinemaService.purchaseTicket(request);

        if (optionalSeat.isEmpty()){
            return ResponseEntity.badRequest().body(new RequestError("The ticket has been " +
                    "already purchased!"));
        }

        return ResponseEntity.ok(cinemaMapper.toSeatResponse(optionalSeat.get()));
    }
}

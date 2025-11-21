package com.cfloresh.springboot.app.cinemarest.mapper;

import com.cfloresh.springboot.app.cinemarest.dto.CinemaRoomDto;
import com.cfloresh.springboot.app.cinemarest.dto.SeatDto;
import com.cfloresh.springboot.app.cinemarest.model.CinemaRoom;
import com.cfloresh.springboot.app.cinemarest.model.Seat;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CinemaMapper {

    public CinemaRoomDto toResponse(CinemaRoom cinemaRoom) {
        CinemaRoomDto response = new CinemaRoomDto(cinemaRoom.getRows(), cinemaRoom.getColumns());
        response.setSeats(cinemaRoom.getSeats().stream().map(this::toSeatResponse).collect(Collectors.toList()));

        return response;
    }

    public SeatDto toSeatResponse(Seat seat) {
        return new SeatDto(seat.getRow(), seat.getColumn(), seat.getPrice());
    }
}

package com.firstspring.reservation.reservation.controller;

import com.firstspring.reservation.reservation.dto.ReservationDto;
import com.firstspring.reservation.reservation.dto.ReservationResponse;
import com.firstspring.reservation.reservation.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService ReservationService;

    public ReservationController(ReservationService ReservationService) {
        this.ReservationService = ReservationService;
    }

    @PostMapping
    public String createReservation(@RequestBody ReservationDto ReservationDto) {
        ReservationService.createReservation(ReservationDto);
        return "reservation created";
    }

    @GetMapping
    public List<ReservationResponse> getAllReservations() {
        return ReservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ReservationResponse getReservation(@PathVariable Long id) {
        return ReservationService.getReservation(id);
    }

    @PutMapping("/{id}")
    public String updateReservation(@PathVariable Long id, @RequestBody ReservationDto ReservationDto) {
        ReservationService.updateReservation(id, ReservationDto);
        return "reservation updated";
    }

    @DeleteMapping("/{id}")
    public String deleteReservation(@PathVariable Long id) {
        ReservationService.deleteReservation(id);
        return "reservation deleted";
    }
}

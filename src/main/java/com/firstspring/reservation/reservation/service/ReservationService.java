package com.firstspring.reservation.reservation.service;

import com.firstspring.reservation.reservation.dto.ReservationDto;
import com.firstspring.reservation.reservation.dto.ReservationResponse;
import java.util.List;

public interface ReservationService {
    void createReservation(ReservationDto ReservationDto);

    List<ReservationResponse> getAllReservations();

    ReservationResponse getReservation(Long id);

    void updateReservation(Long id, ReservationDto ReservationDto);

    void deleteReservation(Long id);
}

package com.firstspring.reservation.reservation.service;

import com.firstspring.reservation.reservation.dto.ReservationDto;
import com.firstspring.reservation.reservation.dto.ReservationResponse;
import java.util.List;

public interface ReservationService {
    ReservationResponse createReservation(ReservationDto dto);

    List<ReservationResponse> getAllReservations();

    ReservationResponse getReservation(Long id);

    void cancelReservation(Long id);
}

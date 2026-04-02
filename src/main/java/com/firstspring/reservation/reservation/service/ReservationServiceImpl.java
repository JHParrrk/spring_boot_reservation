package com.firstspring.reservation.reservation.service;

import com.firstspring.reservation.reservation.dto.ReservationDto;
import com.firstspring.reservation.reservation.dto.ReservationResponse;
import com.firstspring.reservation.reservation.entity.Reservation;
import com.firstspring.reservation.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository ReservationRepository;

    public ReservationServiceImpl(ReservationRepository ReservationRepository) {
        this.ReservationRepository = ReservationRepository;
    }

    @Override
    public void createReservation(ReservationDto ReservationDto) {
        Reservation reservation = new Reservation(null, ReservationDto.title(), ReservationDto.content(),
                ReservationDto.author());
        ReservationRepository.save(reservation);
    }

    @Override
    public List<ReservationResponse> getAllReservations() {
        return ReservationRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationResponse getReservation(Long id) {
        Reservation reservation = ReservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("reservation not found"));
        return toResponse(reservation);
    }

    @Override
    public void updateReservation(Long id, ReservationDto ReservationDto) {
        Reservation reservation = ReservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("reservation not found"));
        reservation.setTitle(ReservationDto.title());
        reservation.setContent(ReservationDto.content());
        ReservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        ReservationRepository.deleteById(id);
    }

    private ReservationResponse toResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getTitle(),
                reservation.getContent(),
                reservation.getAuthor(),
                reservation.getCreatedAt());
    }
}

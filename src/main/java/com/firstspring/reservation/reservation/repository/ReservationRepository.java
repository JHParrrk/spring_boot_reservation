package com.firstspring.reservation.reservation.repository;

import com.firstspring.reservation.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}

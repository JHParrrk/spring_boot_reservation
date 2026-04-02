package com.firstspring.reservation.reservation.repository;

import com.firstspring.reservation.reservation.entity.Reservation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReservationRepository {
    private final List<Reservation> database = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(1L);

    public Reservation save(Reservation reservation) {

        if (reservation.getId() == null) {
            Reservation newreservation = new Reservation(sequence.getAndIncrement(), reservation.getTitle(), reservation.getContent(),
                    reservation.getAuthor());
            database.add(newreservation);
            return newreservation;
        } else {
            // Update
            return reservation;
        }
    }

    public List<Reservation> findAll() {
        return new ArrayList<>(database);
    }

    public Optional<Reservation> findById(Long id) {
        return database.stream()
                .filter(reservation -> reservation.getId().equals(id))
                .findFirst();
    }

    public void deleteById(Long id) {
        database.removeIf(reservation -> reservation.getId().equals(id));
    }
}

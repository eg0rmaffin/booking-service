package ru.prime.service;

import ru.prime.entity.Lot;
import ru.prime.entity.Reservation;
import ru.prime.repository.LotRepository;
import ru.prime.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final LotRepository lotRepository;

    public ReservationService(ReservationRepository reservationRepository, LotRepository lotRepository) {
        this.reservationRepository = reservationRepository;
        this.lotRepository = lotRepository;
    }

    public Reservation bookLot(Reservation reservation) {
        Optional<Lot> lotOptional = lotRepository.findById(reservation.getLot().getId());
        if (lotOptional.isPresent()) {
            reservation.setLot(lotOptional.get());
            return reservationRepository.save(reservation);
        } else {
            throw new IllegalArgumentException("Invalid lot ID: " + reservation.getLot().getId());
        }
    }

}

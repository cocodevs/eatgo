package com.dadongs.eatgo.application;

import com.dadongs.eatgo.domain.Reservation;
import com.dadongs.eatgo.domain.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReservationService {
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation addReservation(Long restaurantId, Long userId, String name, String date, String time, Integer partSize) {
        Reservation reservation = Reservation.builder()
                .restaurantId(restaurantId)
                .userId(userId)
                .name(name)
                .date(date)
                .time(time)
                .partySize(partSize)
                .build();
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservations(long restaurantId) {
        return reservationRepository.findAllByRestaurantId(restaurantId);
    }
}

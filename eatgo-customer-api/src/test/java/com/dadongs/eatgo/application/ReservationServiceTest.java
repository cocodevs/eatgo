package com.dadongs.eatgo.application;

import com.dadongs.eatgo.domain.Reservation;
import com.dadongs.eatgo.domain.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class ReservationServiceTest {

    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void addReservation() {
        Long restaurantId = 100L;
        Long userId = 1L;
        String name = "cocodev";
        String date = "2020-03-24";
        String time = "18:00";
        Integer partySize = 10;

        Reservation mockReservation = Reservation.builder().build();
        given(reservationRepository.save(any())).will(invocation -> {
           Reservation reservation = invocation.getArgument(0);
           return reservation;
        });
        Reservation reservation = reservationService.addReservation(restaurantId, userId, name, date, time, partySize);
        assertThat(reservation.getName(), is(name));
        
        verify(reservationRepository).save(any(Reservation.class));

    }
}
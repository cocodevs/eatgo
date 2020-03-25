package com.dadongs.eatgo.interfaces;

import com.dadongs.eatgo.application.ReservationService;
import com.dadongs.eatgo.domain.Reservation;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void create() throws Exception {
        Long userId = 1L;
        String name = "cocodev";
        String date = "2020-03-24";
        String time = "18:00";
        Integer partySize = 10;

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJjb2NvZGV2In0.g7BJHpONXgWRe-YCBurhTLvB_hG30V45I1Z9TW8qhxs";

        Reservation mockReservation = Reservation.builder().id(userId).build();

        given(reservationService.addReservation(any(), any(), any(), any(), any(), any())).willReturn(mockReservation);

        mvc.perform(post("/restaurants/1/reservations")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"date\":\"2020-03-24\",\"time\":\"18:00\",\"partySize\":10}"))
                .andExpect(status().isCreated());


        verify(reservationService).addReservation(eq(1L), eq(userId), eq(name), eq(date), eq(time), eq(partySize));
    }
}
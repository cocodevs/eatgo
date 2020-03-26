package com.dadongs.eatgo.interfaces;

import com.dadongs.eatgo.application.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void list() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJjb2NvZGV2IiwicmVzdGF1cmFudElkIjoxMDA0fQ.dF8sv83yJCMRebftBhTS7_CDDaBykZcIRPBB-nlOlNo";

        mvc.perform(get("/reservations")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
        verify(reservationService).getReservations(1004L);
    }
}
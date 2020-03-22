package com.dadongs.eatgo.interfaces;

import com.dadongs.eatgo.application.EmailNotExistException;
import com.dadongs.eatgo.application.PasswordWrongException;
import com.dadongs.eatgo.application.UserService;
import com.dadongs.eatgo.domain.User;
import com.dadongs.eatgo.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
class SessionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void createWithVaildAttributes() throws Exception {

        Long id = 1004L;
        String email = "tester@test.com";
        String password = "test";

        User mockUser = User.builder()
                .id(id)
                .email(email)
                .password(password)
                .level(1L)
                .build();

        given(userService.authenticate(email, password)).willReturn(mockUser);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@test.com\",\"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/session"))
                //.andExpect(content().string("{\"accessToken\":\"header.payload.signature\"}"))
                .andExpect(content().string(containsString(".")));

        verify(userService).authenticate(eq(email), eq(password));
    }

    @Test
    public void createRestaurantOwner() throws Exception {

        Long id = 1004L;
        String email = "tester@test.com";
        String name = "tester";
        String password = "test";

        User mockUser = User.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .restaurantId(555L)
                .level(50L)
                .build();

        given(userService.authenticate(email, password)).willReturn(mockUser);

        String mockToken = "header.payload.signature";
        //given(jwtUtil.createToken(id, name, 555L)).willReturn(mockToken);
        //when(jwtUtil.createToken(id, name, 555L)).thenReturn(mockToken);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@test.com\",\"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/session"))
                /*.andExpect(content().string(
                        containsString("{\"accessToken\":\"header.payload.signature\"}")
                ))*/;

        verify(userService).authenticate(eq(email), eq(password));
    }

    @Test
    public void createWithNotExistEmail() throws Exception {
        given(userService.authenticate("x@test.com", "test"))
                .willThrow(EmailNotExistException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"x@test.com\",\"password\":\"test\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("x@test.com"), eq("test"));
    }

    @Test
    public void createWithWrongPassword() throws Exception {
        given(userService.authenticate("tester@test.com", "x"))
                .willThrow(PasswordWrongException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@test.com\",\"password\":\"x\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("tester@test.com"), eq("x"));
    }
}
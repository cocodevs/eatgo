package com.dadongs.eatgo.interfaces;

import com.dadongs.eatgo.application.ReviewService;
import com.dadongs.eatgo.application.UserService;
import com.dadongs.eatgo.domain.Review;
import com.dadongs.eatgo.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void create() throws Exception {
        // email, name, password
        // 201

        User mockUser = User.builder()
                .id(1L)
                .email("tester@test.com")
                .name("tester")
                .password("test")
                .build();
        given(userService.registerUser("tester@test.com", "tester", "test")).willReturn(mockUser);
        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@test.com\",\"name\":\"tester\",\"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/users/1"));

        verify(userService).registerUser(eq("tester@test.com"), eq("tester"), eq("test"));

    }

}
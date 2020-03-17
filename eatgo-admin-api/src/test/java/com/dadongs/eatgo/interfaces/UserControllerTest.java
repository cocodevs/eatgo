package com.dadongs.eatgo.interfaces;

import com.dadongs.eatgo.application.UserService;
import com.dadongs.eatgo.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void list() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .email("emai@email.com")
                .name("테스터")
                .level(1L)
                .build());
        given(userService.getUsers()).willReturn(users);

        mvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void create() throws Exception {
        String email = "admin@test.com";
        String name = "administrator";

        User user = User.builder()
                .email(email)
                .name(name)
                .build();
        given(userService.addUser(email, name)).willReturn(user);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"admin@test.com\",\"name\":\"administrator\"}"))   // {"email":"admin@test.com","name":"administrator"}
                .andExpect(status().isCreated());

        verify(userService).addUser(email, name);
    }

    @Test
    public void update() throws Exception {

        mvc.perform(patch("/users/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"admin@test.com\",\"name\":\"administrator\",\"level\":1}"))   // {"email":"admin@test.com","name":"administrator","level":1}
                .andExpect(status().isOk());

        Long id = 1004L;
        String email = "admin@test.com";
        String name = "administrator";
        Long level = 1L;

        //User user = User.builder().email(email).name(name).level(level).build();

        verify(userService).updateUser(eq(id), eq(email), eq(name), eq(level));
    }

    @Test
    public void deativate() throws Exception {
        mvc.perform(delete("/users/1004"))
                .andExpect(status().isOk());
        verify(userService).deativateUser(1004L);
    }
}
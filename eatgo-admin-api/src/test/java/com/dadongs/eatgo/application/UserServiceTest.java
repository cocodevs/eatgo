package com.dadongs.eatgo.application;

import com.dadongs.eatgo.domain.User;
import com.dadongs.eatgo.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void list(){
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder().email("test@test.com").name("테스터").build());
        given(userService.getUsers()).willReturn(mockUsers);

        List<User> users = userService.getUsers();
        User user = users.get(0);
        assertThat(user.getName(), is("테스터"));
    }

    @Test
    public void addUser() {
        String email = "admin@test.com";
        String name = "administrator";

        User mockUser = User.builder()
                .email(email)
                .name(name)
                .build();
        given(userRepository.save(any())).willReturn(mockUser);

        User user = userService.addUser(email, name);

        assertThat(user.getName(), is(name));
    }

    @Test
    public void updateUser() {
        Long id = 1L;
        String email = "admin@test.com";
        String name = "djkim";
        Long level = 100L;

        User mockUser = User.builder().id(id).email(email).name("admin").level(1L).build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.updateUser(id, email, name, level);

        verify(userRepository).findById(eq(id));

        assertThat(user.getName(), is("djkim"));
        assertThat(user.isAdmin(), is(true));

    }

    @Test
    public void deactiveUser() {
        Long id = 1004L;
        String email = "admin@test.com";
        String name = "djkim";
        Long level = 100L;
        User mockUser = User.builder().id(id).email(email).name(name).level(level).build();
        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.deativateUser(1004L);
        verify(userRepository).findById(1004L);

        assertThat(user.isAdmin(), is(false));
        assertThat(user.isActive(), is(false));

    }
}
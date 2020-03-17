package com.dadongs.eatgo.application;

import com.dadongs.eatgo.domain.User;
import com.dadongs.eatgo.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        userService = new UserService(userRepository);
    }

    @Test
    public void registerUser() {
        String email = "tester@test.com";
        String name = "tester";
        String password = "test";
        userService.registerUser(email, name, password);

        verify(userRepository).save(any());
    }

    @Test
            //(EmailExistedException.class)
    public void registerUserWithExistedEmail() {

        assertThrows(EmailExistedException.class,() -> {
            String email = "tester@test.com";
            String name = "tester";
            String password = "test";

            User mockUser = User.builder().build();
            given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

            userService.registerUser(email, name, password);

            verify(userRepository, never()).save(any());
        });
    }
}
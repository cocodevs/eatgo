package com.dadongs.eatgo.application;

import com.dadongs.eatgo.domain.User;
import com.dadongs.eatgo.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void authenticateWithVaildAttributes() {
        String email = "tester@test.com";
        String password = "test";

        User mockUser = User.builder()
                .email(email)
                .build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(), any())).willReturn(true);

        User user = userService.authenticate(email, password);
        assertThat(user.getEmail(), is(email));
    }

    @Test
    public void authenticateWithNotExistedEmail() {
        assertThrows(EmailNotExistException.class, () -> {
            String email = "x@test.com";
            String password = "test";

            given(userRepository.findByEmail(email)).willReturn(Optional.empty());

            userService.authenticate(email, password);
        });
    }

    @Test
    public void authenticateWithWrongPassword() {
        assertThrows(PasswordWrongException.class, () -> {
            String email = "tester@test.com";
            String password = "x";

            User mockUser = User.builder()
                    .email(email)
                    .build();

            given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

            given(passwordEncoder.matches(any(), any())).willReturn(false);

            userService.authenticate(email, password);
        });
    }

    @Test
    public void accessToken() {
        User user = User.builder().password("ACCESSTOKEN").build();

        //assertThat(user.getAccessToken(), is("ACCESSTOKE"));
    }
}
package com.dadongs.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void creation(){
        User user = User.builder()
                .email("tester@test.com")
                .name("테스터")
                .level(100L)
                .build();

        assertThat(user.getName(), is("테스터"));
        assertThat(user.isAdmin(), is(true));
    }

    @Test
    public void restaurantOwner() {
        User user = User.builder()
                .level(1L)
                .build();
        assertThat(user.isRestaurantOwner(), is(false));

        user.setRestaurantId(1004L);
        assertThat(user.isRestaurantOwner(), is(true));
        assertThat(user.getRestaurantId(), is(1004L));

    }
}
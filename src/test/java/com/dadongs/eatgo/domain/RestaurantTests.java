package com.dadongs.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class RestaurantTests {

    @Test
    public void creation(){
        Restaurant restaurant = new Restaurant();
        assertThat(restaurant.getName(), is("Bob Zip"));
    }
}
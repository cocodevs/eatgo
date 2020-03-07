package com.dadongs.eatgo.application;

import com.dadongs.eatgo.domain.MenuItem;
import com.dadongs.eatgo.domain.MenuItemRepository;
import com.dadongs.eatgo.domain.Restaurant;
import com.dadongs.eatgo.domain.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

public class RestaurantServiceTest {

    @Mock
    private RestaurantService restaurantService;
    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    //@Before //@Before 모든 test를 수행하기 전에 항상 수행
    public void setUp(){
        MockitoAnnotations.initMocks(this); // @Mock이 붙어있는 객체 초기화
        mockRestaurantRepository();
        mockMenuItemRepository();

        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant(1004L, "Bob Zip", "Seoul");
        restaurants.add(restaurant);
        given(restaurantRepository.findAll()).willReturn(restaurants);

        given(restaurantRepository.findById(1004L)).willReturn(restaurant);
    }

    private void mockMenuItemRepository(){
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Kimchi"));

        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(), is(1004L));
    }

    @Test
    public void getRestaurant(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
        assertThat(restaurant.getId(), is(1004L));

        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertThat(menuItem.getName(), is("Kimchi"));
    }

}
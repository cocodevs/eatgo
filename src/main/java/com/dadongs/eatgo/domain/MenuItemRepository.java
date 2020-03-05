package com.dadongs.eatgo.domain;

import com.dadongs.eatgo.domain.MenuItem;

import java.util.List;

public interface MenuItemRepository {
    List<MenuItem> findAllByRestaurantId(Long restaurantId);
}

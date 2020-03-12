package com.dadongs.eatgo.application;

import com.dadongs.eatgo.domain.MenuItem;
import com.dadongs.eatgo.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public void bulkUpdate(long restaurantId, List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems){
            update(restaurantId, menuItem);
        }
    }

    private void update(long restaurantId, MenuItem menuItem) {
        if(menuItem.isDestroy()){
            menuItemRepository.deleteById(menuItem.getId());
            return;
        }
        menuItem.setRestaurantId(restaurantId);
        menuItemRepository.save(menuItem);
    }
}
package com.dadongs.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAll();

    List<Restaurant> findAllByAddressContaining(String region);

    List<Restaurant> findAllByAddressContainingAndCategoryId(String region, long categoryId);

    Optional<Restaurant> findById(Long id); // Optional : null  접근 문제 해결

    Restaurant save(Restaurant restaurant);
}
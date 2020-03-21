package com.dadongs.eatgo.application;

import com.dadongs.eatgo.domain.Review;
import com.dadongs.eatgo.domain.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
         this.reviewRepository = reviewRepository;
    }

    public Review addReview(Long restaurantId, String name, Integer score, String description) {
        Review review = Review.builder()
                .name(name)
                .score(score)
                .description(description)
                .build();
        review.setRestaurantId(restaurantId);
        return reviewRepository.save(review);
    }
}

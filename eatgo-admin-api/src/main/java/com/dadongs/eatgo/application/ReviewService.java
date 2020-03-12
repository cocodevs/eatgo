package com.dadongs.eatgo.application;

import com.dadongs.eatgo.domain.Review;
import com.dadongs.eatgo.domain.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
         this.reviewRepository = reviewRepository;
    }

    public Review addReview(Long restaurantId, Review review) {
        review.setRestaurantId(restaurantId);
        return reviewRepository.save(review);
    }

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }
}

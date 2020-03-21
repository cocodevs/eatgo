package com.dadongs.eatgo.application;

import com.dadongs.eatgo.application.ReviewService;
import com.dadongs.eatgo.domain.Review;
import com.dadongs.eatgo.domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class ReviewServiceTest {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void addReview(){
        Review review = Review.builder()
                .name("djkim")
                .score(4)
                .description("good")
                .build();
        reviewService.addReview(1L, "djkim", 4, "good");

        verify(reviewRepository).save(any());
    }
}
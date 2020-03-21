package com.dadongs.eatgo.interfaces;

import com.dadongs.eatgo.application.ReviewService;
import com.dadongs.eatgo.domain.Review;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> create(
            Authentication authentication,
            @PathVariable("restaurantId") Long restaurantId,
            @Valid @RequestBody Review resource
    ) throws URISyntaxException {

        Claims claims = (Claims) authentication.getPrincipal();

        Review review = reviewService.addReview(restaurantId, claims.get("name", String.class), resource.getScore(), resource.getDescription());

        String url = "/restaurants/" + restaurantId + "/reviews/" + review.getId();

        return ResponseEntity.created(new URI(url))
                .body("{}");
    }
}

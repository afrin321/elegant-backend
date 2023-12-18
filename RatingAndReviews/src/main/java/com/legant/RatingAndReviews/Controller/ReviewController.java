package com.legant.RatingAndReviews.Controller;

import com.legant.RatingAndReviews.Dto.ReviewRequestResponse;
import com.legant.RatingAndReviews.Dto.User;
import com.legant.RatingAndReviews.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews/")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public String addReview(@RequestBody ReviewRequestResponse reviewRequest) {
        return reviewService.addReview(reviewRequest);
    }

    @GetMapping("/reviews")
    public List<ReviewRequestResponse> getReviewsByProduct(@RequestParam Long productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    @GetMapping("/rating")
    public int getProductRating(@RequestParam Long productId) {
        return reviewService.getRating(productId);
    }

    @GetMapping("/user")
    public User getUserById(@RequestParam Long userId) {
        return reviewService.getUserById(userId);
    }



}

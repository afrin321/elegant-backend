package com.legant.RatingAndReviews.Service;

import com.legant.RatingAndReviews.Dto.ReviewRequestResponse;
import com.legant.RatingAndReviews.Dto.User;
import com.legant.RatingAndReviews.Model.Reviews;
import com.legant.RatingAndReviews.Repo.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private WebClient webClient;

    public String addReview(ReviewRequestResponse reviewRequest) {
        Reviews reviews = Reviews.builder()
            .userId(reviewRequest.getUserId())
            .productId(reviewRequest.getProductId())
            .rating(reviewRequest.getRating())
            .review(reviewRequest.getReview())
                    .build();

        reviewRepo.save(reviews);

        return "Review added successfully";
    }

    public int getRating(Long productId) {
        return reviewRepo.findAverageOfRatingByProductId(productId).intValue();
    }

    public List<ReviewRequestResponse> getReviewsByProductId(Long productId) {
        List<Reviews> reviews = reviewRepo.findByProductId(productId);



        return reviews.stream().map(review -> ReviewRequestResponse.builder()
                .reviewId(review.getReviewId())
                .userId(review.getUserId())
                .productId(review.getProductId())
                .review(review.getReview())
                .rating(review.getRating())
                .user(null)
                .build()).collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        return webClient.get()
                .uri("http://localhost:8082/api/user/product?userId=" + id.toString())
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }




}

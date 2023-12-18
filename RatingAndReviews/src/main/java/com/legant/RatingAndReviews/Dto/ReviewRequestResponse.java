package com.legant.RatingAndReviews.Dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReviewRequestResponse {

    private Long reviewId;
    private Long userId;
    private Long productId;
    private String review;
    private int rating;
    private User user;

}

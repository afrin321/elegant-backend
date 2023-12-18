package com.legant.RatingAndReviews.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReviewResponseByProduct {

    private int rating;
    private List<ReviewRequestResponse> reviews;

}

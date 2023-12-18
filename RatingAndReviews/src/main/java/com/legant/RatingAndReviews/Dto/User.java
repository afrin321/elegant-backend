package com.legant.RatingAndReviews.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Long userId;
    private String username;
    private String photo;

}

package com.legant.RatingAndReviews.Repo;

import com.legant.RatingAndReviews.Model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Reviews, Long> {

    List<Reviews> findByProductId(Long productId);
    List<Reviews> findByUserId(Long userId);

    @Query("SELECT AVG(r.rating) from Reviews r WHERE r.productId = :productId")
    Double findAverageOfRatingByProductId(@Param("productId") Long productId);


}

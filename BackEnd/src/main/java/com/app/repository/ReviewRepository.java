package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByUserIdAndProductId(Long userId, Long productId);

    Optional<List<Review>> findAllByProductId(Long productId);

       long countAllByProductIdAndReviewMessageNotNull (Long productId);
       
    long countAllByProductId(long productId);
}


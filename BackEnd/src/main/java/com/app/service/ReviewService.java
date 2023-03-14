package com.app.service;



import java.util.List;

import com.app.dto.web_product.CreateOrUpdateReviewRequest;
import com.app.entity.Review;

public interface ReviewService {

    void createOrUpdateReview(CreateOrUpdateReviewRequest createOrUpdateReviewRequest);

    List<Review> getReviewsForProduct(Long productId);

}

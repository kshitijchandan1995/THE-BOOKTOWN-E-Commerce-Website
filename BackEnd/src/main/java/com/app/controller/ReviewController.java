package com.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.web_product.CreateOrUpdateReviewRequest;
import com.app.entity.Review;
import com.app.service.ReviewService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<?> createOrUpdateReview(@RequestBody CreateOrUpdateReviewRequest createOrUpdateReviewRequest) {

        reviewService.createOrUpdateReview(createOrUpdateReviewRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/review1/{productId}")
    public ResponseEntity<?> getAllReviewsForProduct(@PathVariable("productId") String productId) {
        List<Review> reviewsForProduct = reviewService.getReviewsForProduct(Long.valueOf(productId));
        System.out.println(reviewsForProduct.get(0).getRatingValue()+"ratinng value");
       // reviewsForProduct.forEach(System.out::println);
        return ResponseEntity.ok(reviewsForProduct);
    }
}
	
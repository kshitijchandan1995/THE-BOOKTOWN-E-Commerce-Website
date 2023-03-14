package com.app.dto.web_product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long productId;
    private String productName;
    private String description;
    private double price;

    private int availableItemCount;
    private Double averageRating;
    private int noOfRatings;
    private String imageId;

}

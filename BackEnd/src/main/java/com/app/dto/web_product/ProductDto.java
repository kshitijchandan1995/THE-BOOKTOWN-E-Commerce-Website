package com.app.dto.web_product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
private String productName;
private double price;
private String description;
private Long productCategoryId;
private int availableItemCount;
}

package com.app.dto.web_product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {

    @NotNull(message = "productId should not be null!")
    @NotEmpty(message = "productId should not be empty!")
    private Long productId;

    private String productName;

    private String description;

    @Min(value = 0)
    private Double price;

    private String imageId;
    
    

    private Long productCategoryId;

    private Integer availableItemCount;

}

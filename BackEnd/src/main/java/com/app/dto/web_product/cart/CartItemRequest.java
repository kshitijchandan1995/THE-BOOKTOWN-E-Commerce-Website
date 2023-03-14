package com.app.dto.web_product.cart;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemRequest {
    
    @NotNull(message = "productId should not be null!")
    @NotEmpty(message = "productId should not be empty!")
    private Long productId;

    @Min(message = "quantity should be greater than 0", value = 1)
    private Integer quantity;
    
}

package com.app.dto.web_product.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreviewOrderRequest {

	@NotBlank
    private Long AddressId;
    
    @NotBlank
    private String userName;
}

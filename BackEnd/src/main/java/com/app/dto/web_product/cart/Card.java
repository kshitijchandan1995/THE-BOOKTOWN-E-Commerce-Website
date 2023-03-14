package com.app.dto.web_product.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private Long paymentMethodId;
    private String cardBrand;
    private String last4Digits;
}

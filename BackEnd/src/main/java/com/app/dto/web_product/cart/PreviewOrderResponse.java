package com.app.dto.web_product.cart;


import java.util.ArrayList;
import java.util.List;

import com.app.entity.AddressDao;
import com.app.entity.OrderItem;
import com.app.entity.OrderShippingAddress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreviewOrderResponse {
    private List<OrderItem> orderItems = new ArrayList<>();
    private AddressDao Address;
//    private GetAddressResponse billingAddress;
//    private Card card;
    private Double itemsTotalPrice;
    private Double taxPrice;
    private Double shippingPrice;
    private Double totalPrice;
}

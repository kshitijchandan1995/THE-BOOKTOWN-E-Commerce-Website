package com.app.dto.web_product.Address;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAddressResponse {

    private Long addressId;
    private Long userId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String phone;
    
}

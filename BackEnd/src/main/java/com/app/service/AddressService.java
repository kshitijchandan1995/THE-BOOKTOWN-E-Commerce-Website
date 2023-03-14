package com.app.service;



import java.util.List;

import com.app.dto.web_product.Address.CreateAddressRequest;
import com.app.dto.web_product.Address.GetAddressResponse;
import com.app.dto.web_product.Address.UpdateAddressRequest;


public interface AddressService {

  Long createAddress(CreateAddressRequest createAddressRequest);

  List<GetAddressResponse> getAddress();

  void updateAddress(UpdateAddressRequest updateAddressRequest);

  GetAddressResponse getAddressById(Long addressId);

  void deleteAddressById(Long addressId);
}

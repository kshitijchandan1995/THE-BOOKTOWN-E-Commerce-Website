package com.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.web_product.Address.CreateAddressRequest;
import com.app.dto.web_product.Address.GetAddressResponse;
import com.app.dto.web_product.Address.UpdateAddressRequest;
import com.app.service.AddressService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AddressController {
    
    @Autowired
    AddressService addressService;
    
    @PostMapping("/address")
    public ResponseEntity<Long> createAddress(@RequestBody CreateAddressRequest createAddressRequest) {
        Long add=addressService.createAddress(createAddressRequest);
        return ResponseEntity.ok(add);
    }

    @PutMapping("/address")
    public ResponseEntity<Object> updateAddress(@RequestBody UpdateAddressRequest updateAddressRequest) {
        addressService.updateAddress(updateAddressRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/address")
    public ResponseEntity<List<GetAddressResponse>> getAddress() {
        List<GetAddressResponse> address = addressService.getAddress();
        return ResponseEntity.ok(address);
    }

    @GetMapping("/address/{addressId}")
    public ResponseEntity<GetAddressResponse> getAddressById(@PathVariable("addressId") Long addressId) {
        GetAddressResponse address = addressService.getAddressById(addressId);
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/address/{addressId}")
    public ResponseEntity<?> deleteAddressById(@PathVariable("addressId") Long addressId) {
        addressService.deleteAddressById(addressId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}

package com.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.web_product.cart.CreateCartResponse;
import com.app.service.CartService;


@RestController
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart")
//    @PreAuthorize("hasAuthority('STANDARD_USER') or hasAuthority('ADMIN_USER')" )
    public ResponseEntity<CreateCartResponse> createCart() {

        Long cartId = cartService.createCart();

        CreateCartResponse createCartResponse = CreateCartResponse.builder()
                .cartId(cartId)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(createCartResponse);
    }
    
    @GetMapping("/cart")
    public ResponseEntity<?> getCart(){
    
        return ResponseEntity.ok(cartService.getCart());
        
    }

}

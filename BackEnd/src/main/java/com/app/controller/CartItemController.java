package com.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.web_product.cart.CartItemRequest;
import com.app.service.CartItemService;


@RestController
@CrossOrigin
public class CartItemController {

    @Autowired
    CartItemService cartItemService;
    
    @PostMapping("/cart/cartItem")
//    @ResponseStatus(value = HttpStatus.OK)
    public void addCartItem(@RequestBody CartItemRequest cartItemRequest) {
        cartItemService.addCartItem(cartItemRequest);
    }
    
    @DeleteMapping("/cart/cartItem/{cartItemId}")
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeCartItem(@PathVariable(value = "cartItemId") Long cartItemId) {
        cartItemService.removeCartItem(cartItemId);
    }
    
    @DeleteMapping("/cart/cartItem")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeAllCartItems(@RequestParam(value = "cartId") Long cartId) {
        cartItemService.removeAllCartItems(cartId);
    }
    @GetMapping("/cart/cartItems/{username}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> grtAllCartItems(@PathVariable String username){
//        cartItemService.removeAllCartItems(cartId);
        return ResponseEntity.ok(cartItemService.getallcartitems(username));
    }
    
    @GetMapping("/cart/setincreamentquantity/{cartItemId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> setcartitemsquantityincreament(@PathVariable Long cartItemId){
//        cartItemService.removeAllCartItems(cartId);
        return ResponseEntity.ok(cartItemService.setquantityplusone(cartItemId));
    }
    
    @GetMapping("/cart/setdecreamentquantity/{cartItemId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<?> setcartitemsquantitydecreament(@PathVariable Long cartItemId){
//        cartItemService.removeAllCartItems(cartId);
        return ResponseEntity.ok(cartItemService.setquantityminusone(cartItemId));
    }
}

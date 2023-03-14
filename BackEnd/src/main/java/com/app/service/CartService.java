package com.app.service;

import com.app.entity.Cart;

public interface CartService {

    Cart getCart();
    
    Cart getCartByCartId(Long cartId);

    Long createCart();

    Cart getCartByUserName(String userName);

}

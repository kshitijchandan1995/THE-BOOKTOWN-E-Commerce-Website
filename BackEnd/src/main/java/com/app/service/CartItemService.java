package com.app.service;

import java.util.List;

import com.app.dto.web_product.cart.CartItemRequest;
import com.app.entity.CartItem;

public interface CartItemService {

    void addCartItem(CartItemRequest cartItemRequest);
    void removeCartItem(Long cartItemId);
    CartItem getCartItem(Long cartItemId);
    void removeAllCartItems(Long cartId);
//	List<CartItem> getallcartitems();
	List<CartItem> getallcartitems(String username);
	String setquantityplusone(Long cartItemId);
	String setquantityminusone(Long cartItemId);

}

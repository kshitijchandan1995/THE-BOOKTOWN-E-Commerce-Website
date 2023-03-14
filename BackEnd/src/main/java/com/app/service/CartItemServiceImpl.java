package com.app.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.dto.web_product.ProductResponse;
import com.app.dto.web_product.cart.CartItemRequest;
import com.app.entity.Cart;
import com.app.entity.CartItem;
import com.app.repository.CartItemRepository;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartService cartService;

   

    @Autowired
    CartItemRepository cartItemRepository1;
    
    @Autowired
    ProductService productService;
    
    

    @Override
    public void addCartItem(CartItemRequest cartItemRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) authentication.getPrincipal();
        Cart cartByUserName = cartService.getCartByUserName(userName);

        synchronized (CartServiceImpl.class) {
            if (cartByUserName == null) {
                //create cart for user if not exists.
                cartService.createCart();
                cartByUserName = cartService.getCartByUserName(userName);
            }
        }
    
        ProductResponse getProductResponse = productService.getProduct(cartItemRequest.getProductId());

        if (cartItemRequest.getQuantity() > getProductResponse.getAvailableItemCount()) {
            throw new RuntimeException("Quantity is greater than available item count!");
        }

        //If the product already exists in the cart, update its quantity and itemPrice.

        if (cartByUserName.getCartItems() != null) {
            for (CartItem ci : cartByUserName.getCartItems()) {
    
                if (getProductResponse.getProductId().equals(ci.getProductId())) {
                    ci.setQuantity(cartItemRequest.getQuantity());
                    ci.setItemPrice(getProductResponse.getPrice());
                    ci.setExtendedPrice(ci.getQuantity() * getProductResponse.getPrice());
                    cartItemRepository1.save(ci);
                    return;
                }
            }
        }

        //If cart doesn't have any cartItems, then create cartItems.
        CartItem cartItem = CartItem.builder()
                                    .cart(cartByUserName)
                                    .itemPrice(getProductResponse.getPrice())
                                    .extendedPrice(cartItemRequest.getQuantity() * getProductResponse.getPrice())
                                    .quantity(cartItemRequest.getQuantity())
                                    .productId(getProductResponse.getProductId())
                                    .productName(getProductResponse.getProductName())
                                    .build();

        cartItemRepository1.save(cartItem);
    }

    @Override
    public void removeCartItem(Long cartItemId) {
        CartItem cartItem = this.getCartItem(cartItemId);
        cartItemRepository1.delete(cartItem);
    }

    @Override
    public CartItem getCartItem(Long cartItemId) {
       CartItem byCartItemId = cartItemRepository1.findByCartItemId(cartItemId);
        return byCartItemId;
    }

    @Override
    public void removeAllCartItems(Long cartId) {

        Cart cart = cartService.getCartByCartId(cartId);
        List<Long> cartItemIds = cart.getCartItems().stream().map(cartItem -> cartItem.getCartItemId()).collect(Collectors.toList());
        if (!cartItemIds.isEmpty()) {
            cartItemIds.forEach(this::removeCartItem);
        }
    }

	@Override
	public List<CartItem> getallcartitems(String username) {
//		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	        String userName = (String) authentication.getPrincipal();
	        System.out.println(username+"inside service");
	        Cart cartByUserName = cartService.getCartByUserName(username);
	       System.out.println(cartByUserName.getCartId());
		return  cartByUserName.getCartItems();
	}

	@Override
	public String setquantityplusone(Long cartItemId) {
		System.out.println("11"+cartItemId);
	CartItem	  cartitem=cartItemRepository1.findByCartItemId(cartItemId);
	System.out.println("setquantity");
//	CartItem cartitem=cartitem1.get();
	System.out.println("11");
		cartitem.setQuantity(cartitem.getQuantity()+1);
		System.out.println("22");
		cartItemRepository1.save(cartitem);
		return "Quantity is increased by 1";
	}

	@Override
	public String setquantityminusone(Long cartItemId) {
		CartItem	  cartitem=cartItemRepository1.findByCartItemId(cartItemId);
//		CartItem cartitem=cartitem1.get();
		cartitem.setQuantity(cartitem.getQuantity()-1);
		cartItemRepository1.save(cartitem);
		return "Quantity is deacresed  by 1";
	}
	
}

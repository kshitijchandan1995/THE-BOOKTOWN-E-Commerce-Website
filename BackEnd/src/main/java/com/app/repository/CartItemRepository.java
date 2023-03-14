package com.app.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Cart;
import com.app.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Modifying
    @Transactional
    void deleteByCartItemId(Long cartItemId);

   CartItem findByCartItemId(Long cartItemId);
    
//    @Query(value="select u from CART_ITEM u where u.cart_id = ?1",nativeQuery =  = Cart.class)
	Optional<List<CartItem>> findBycart(Long cart_id);
}
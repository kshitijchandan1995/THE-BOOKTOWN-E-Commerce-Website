package com.app.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Cart;

@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findCartByUserName(String userName);

    Optional<Cart> findByCartId(Long cartId);
}

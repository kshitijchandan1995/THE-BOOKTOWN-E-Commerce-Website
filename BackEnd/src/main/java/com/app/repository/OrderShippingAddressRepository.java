package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.OrderShippingAddress;

public interface OrderShippingAddressRepository extends JpaRepository<OrderShippingAddress, Long> {
    OrderShippingAddress findByOrderId(Long orderId);
    
    OrderShippingAddress findByorderShippingId(Long orderShippingId);
}
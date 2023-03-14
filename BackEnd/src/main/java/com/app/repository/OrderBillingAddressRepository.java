package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.OrderBillingAddress;

public interface OrderBillingAddressRepository extends JpaRepository<OrderBillingAddress, Long> {
    OrderBillingAddress findByOrderId(Long orderId);
}
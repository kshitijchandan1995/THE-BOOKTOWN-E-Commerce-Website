package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.UserPaymentCustomer;

@Repository
public interface UserPaymentCustomerRepository extends JpaRepository<UserPaymentCustomer, Long> {

//    UserPaymentCustomer findByUserId(String userId);
}
package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.*;

public interface OTPRepository extends JpaRepository<OTP, String> {

}

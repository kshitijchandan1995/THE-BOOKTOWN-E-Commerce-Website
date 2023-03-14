package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.OTP;
import com.app.repository.OTPRepository;

@Service
@Transactional
public class OTPServiceImpl implements OTPService {
	
	@Autowired
	private OTPRepository otpRepo;

	@Override
	public OTP getUserOTPbyUserName(String userName) {
		
		return otpRepo.findById(userName).get();
	}

}

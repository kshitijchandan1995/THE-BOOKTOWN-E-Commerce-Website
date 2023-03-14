package com.app.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	SimpleMailMessage sendMail(String userName);

	SimpleMailMessage sendPasswordChangeOTPMail(String userName);
}

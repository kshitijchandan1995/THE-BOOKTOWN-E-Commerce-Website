package com.app.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.EmailService;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SendEmailController {
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private EmailService emailService;
	
	@PostConstruct
	public void init()
	{
		System.out.println("in init "+sender);
	}
	
	@GetMapping("/sendmail/{userName}")
	public ResponseEntity<?> sendPasswordMail(@PathVariable String userName) {
		SimpleMailMessage mesg = emailService.sendMail(userName);
		sender.send(mesg);
		return ResponseEntity.ok("Sent mail");
	}
	
	@PostMapping("/forgot_password/{userName}")
	public ResponseEntity<?> sendForgotPasswordMailOTP(@PathVariable String userName) {
		SimpleMailMessage mesg = emailService.sendPasswordChangeOTPMail(userName);
		sender.send(mesg);
		return ResponseEntity.ok("Sent OTP");
	}
	
}

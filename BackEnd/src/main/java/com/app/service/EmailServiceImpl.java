package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.OTP;
import com.app.entity.OTPGenerator;
import com.app.entity.User;
import com.app.repository.EmailMessageRepository;
import com.app.repository.OTPRepository;
import com.app.repository.UserRepository;



@Service
@Transactional
public class EmailServiceImpl implements EmailService {

	
	@Autowired
	UserRepository user;
	
	@Autowired
	 OTPRepository oTPRepo;
	
	@Override
	public SimpleMailMessage sendMail(String userName) {
		 System.out.println("userName"+userName);
		 User user1 = user.findByUserName(userName).orElseThrow(()-> new ResourceNotFoundException("Invalid User Name"));	
		 System.out.println(user1);
		SimpleMailMessage mesg = new SimpleMailMessage();
			mesg.setTo(user1.getEmail());
			mesg.setSubject("Order Successfully placed");
		 mesg.setText(EmailMessageRepository.Email(user1.getFirstName()+" "+user1.getLastName()+" "));
		 return mesg;
		
	}



	@Override
	public SimpleMailMessage sendPasswordChangeOTPMail(String userName) {
		User user1 = user.findByUserName(userName).orElseThrow(()-> new ResourceNotFoundException("Invalid User Name"));	
		String otp = OTPGenerator.OTP();
		OTP otpData = new OTP(userName,otp);
		oTPRepo.save(otpData);
		SimpleMailMessage mesg = new SimpleMailMessage();
//			Employee emp = empRepo.findByEmployeeCredentialsUserName(userName).orElseThrow(()-> new ResourceNotFoundException("Invalid User Name"));
			mesg.setTo(user1.getEmail());
			mesg.setSubject("OTP for password change request");
		 mesg.setText(EmailMessageRepository.passwordChangeOTPEmail(user1.getFirstName()+" "+user1.getLastName(),otp));
		 return mesg;
	
	}

}

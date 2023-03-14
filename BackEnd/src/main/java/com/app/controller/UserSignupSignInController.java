package com.app.controller;


import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.web_product.ApiResponse;
import com.app.dto.web_product.AuthenticationRequest;
import com.app.dto.web_product.CreateUserRequest;
import com.app.dto.web_product.LoginResponse;
import com.app.dto.web_product.UpdatePassword;
import com.app.entity.OTP;
import com.app.jwt_utills.JwtUtils;
import com.app.service.IUserService;
import com.app.service.OTPService;
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserSignupSignInController {

	// auto wire Authentication Manager for user authentication , created in
	// Security Config class
	// (currently based upon user details service)
	@Autowired
	private AuthenticationManager authManager;
	// auto wire JwtUtils for sending signed JWT back to the clnt
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private IUserService userService;
	
	
	@Autowired
	private OTPService otpService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> userRegistration(@RequestBody CreateUserRequest request) {
//		request.setRoleNames(roleNames);
		System.out.println("in user reg " + request.getRoleNames());
		return ResponseEntity.ok(userService.registerUser(request));
	}

	// add end point for user authentication
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request) {
		System.out.println("in auth " + request);
		try {
			Authentication authenticate = authManager.authenticate

			(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
			// => successful authentication : create JWT n send it to the clnt in the
			// response.
			System.out.println("auth success " + authenticate);
			return ResponseEntity.ok(
					new LoginResponse(jwtUtils.generateJwtToken(authenticate),userService.getRolebyusername(request.getUserName().toString())));
					
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("User authentication Failed", e);
		}

	}
	@PostMapping("/change_password")
	public ResponseEntity<?> updateUserPassword(@RequestBody UpdatePassword newPassDetails) {
		OTP otp = otpService.getUserOTPbyUserName(newPassDetails.getUserName());
		
		System.out.println("DATABASE OTP :: "+ otp.getOtp().length()+" -- USER OTP ::"+newPassDetails.getOtp().length());
		System.out.println("DATABASE OTP :: "+ otp.getOtp()+" -- USER OTP ::"+newPassDetails.getOtp());
		System.out.println("DATABASE OTP :: "+ Integer.valueOf(otp.getOtp())+" -- USER OTP :: "+Integer.valueOf(newPassDetails.getOtp()));
		
		
		if (Integer.valueOf(otp.getOtp())==Integer.parseInt(newPassDetails.getOtp())) {
			if (LocalTime.now().isBefore(otp.getTime())) {
				return ResponseEntity.ok(userService.updateUserPassword(newPassDetails.getUserName(), newPassDetails.getPassword()));
			} else {
				return ResponseEntity.status(HttpStatus.GONE).body(new ApiResponse("OTP Expired!!!"));
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Entered Wrong OTP"));
		}
	}
}


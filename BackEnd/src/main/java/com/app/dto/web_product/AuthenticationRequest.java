package com.app.dto.web_product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthenticationRequest {
	private String userName;
	private String  password;
	
	
}
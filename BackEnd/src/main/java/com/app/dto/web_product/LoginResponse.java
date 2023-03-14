package com.app.dto.web_product;

import java.util.List;
import java.util.Set;

import com.app.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class LoginResponse {

	private String jwt;
	
	private List<Role> roles;
	
	 
}
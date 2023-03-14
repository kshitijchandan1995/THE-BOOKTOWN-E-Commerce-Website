package com.app.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.filters.JwtRequestFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Autowired
	private JwtRequestFilter filter;

	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
		System.out.println("In Authorize Request of Url Mappers");

		http
		.cors()
		.and()
		.csrf().disable()
		.exceptionHandling()
		.authenticationEntryPoint((request, response, ex) -> {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
		})
		.and()
		.authorizeRequests()
		//Get all Employee details which only admin and HR Department
		.antMatchers("/product/product/{productId}").hasRole("ADMIN")
		.antMatchers("product/create").hasRole("VENDOR")
		.antMatchers("/product/{Productid}/image").permitAll()
		.antMatchers("/users").permitAll()
		.antMatchers("/user").permitAll()
		.antMatchers("/role").permitAll()
		.antMatchers("/roles").permitAll()
		.antMatchers("/user/{userId}").permitAll()
		.antMatchers( "/auth/**", "/swagger*/**", "/v*/api-docs/**").permitAll()
		.antMatchers("/product/products/get").permitAll()
		.antMatchers("/productCategory").permitAll()
		.antMatchers("/product/create").permitAll()
		.antMatchers("/cart/cartItem").permitAll()
		.antMatchers("/cart/cartItems/{username}").permitAll()
		.antMatchers("/cart/cartItem/{cartItemId}").permitAll()
		.antMatchers("/address").permitAll()
		.antMatchers("/order").permitAll()
		.antMatchers("/product/product").permitAll()
		.antMatchers("/product/product/{productId}").permitAll()
		.antMatchers("/sendmail/{userName}").permitAll()
		.antMatchers("/cart/setincreamentquantity/{cartItemId}").permitAll()
		.antMatchers("/cart/setdecreamentquantity/{cartItemId}").permitAll()
		.antMatchers("/product/products/get/{CategoryId}").permitAll()
		.antMatchers("/forgot_password/{userName}").permitAll()
		
		
		
		
		
		
		
		
		.antMatchers("employee/prof_image/{empUserName}").permitAll()
//		.antMatchers("employee/gov_id_image/{empId}").access("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
//		.antMatchers("employee/govern_id_image/{empId}").access("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
		.antMatchers("/employee/profile").access("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
		.antMatchers("/employee/name/{userName}").access("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('EMPLOYEE')")
		.antMatchers("/applicants/view").hasRole("ADMIN")
		.antMatchers("/applicant/profile").hasRole("APPLICANT")
		.antMatchers("/applicant/profile_image/{appUserName}").hasRole("APPLICANT")
//		.antMatchers("/applicant/gov_id_image/{appId}").hasRole("APPLICANT")
//		.antMatchers("/applicant/govern_id_image/{appId}").hasRole("APPLICANT")
		.antMatchers("/applicant/prof_image/{appUserName}").hasRole("APPLICANT")
		.antMatchers("/applicant/name/{userName}").hasRole("APPLICANT")

		.antMatchers( "/auth/**", "/swagger*/**", "/v*/api-docs/**").permitAll()
		.antMatchers("/auth/products/get").permitAll()
		.antMatchers("/review1/{productId}","/review").permitAll()

		.antMatchers(HttpMethod.OPTIONS).permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public PasswordEncoder encoder()
	{
		System.out.println("in Password Encoder");
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authenticatonMgr(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}

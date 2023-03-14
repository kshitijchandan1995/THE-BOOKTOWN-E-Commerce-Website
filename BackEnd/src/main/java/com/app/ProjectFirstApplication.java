package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProjectFirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectFirstApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer configure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry regs) {
				regs.addMapping("/").allowedOrigins("*");
			}
		};
	}
	
//	 @Bean
//	    public JavaMailSender javaMailSender() { 
//	          return new JavaMailSenderImpl();
//	    }
}

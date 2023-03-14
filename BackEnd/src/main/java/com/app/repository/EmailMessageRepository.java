package com.app.repository;

public class EmailMessageRepository {
	public static String Email(String name) {
		String message = "Hi "+name+",\r\n"
				+"Your order has been successfully placed.\r\n"
				+ "We are committed to serving you with utmost regard for your safety. "
				+"Know more about our precautionary measures here.\r\n"
				+ "Please note, the delivery date of your order may change based on the government's zonal advisory in your area.\r\n"
				+ " \r\n"
				+ "Thank you for shopping with MEDTECH!\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "Got Questions? Please get in touch with our 24x7 Customer Care..\r\n"
				+ ""
				+"";
		return message;
	}

	public static String passwordChangeOTPEmail(String name, String oTP) {
		String message = "Dear "+name+",\r\n"
				+"Please use the below One Time Password (OTP) to reset your password. This will be valid for 1 hour only.\r\n"
				+ "One Time Password (OTP) : "+oTP+"\r\n"
				+"If you are unable to change the password within 15 minutes of OTP generation, please click on \"Forgot Password\" and continue with the same process again.\r\n"
				+ "Wish you all the best!\r\n"
				+ "Note: Use Chrome browser for best experience. \r\n"
				+ "Regards\r\n"
				+ "THE BOOK TOWN Team\r\n";
				
		return message;
	}
}

package com.app.service;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.web_product.ApiResponse;



public interface ImageHandlingService {

	ApiResponse uploadImage(Long Productid, MultipartFile imageFile) throws IOException;
	
	byte[] serveImage(Long	Productid) throws IOException;
}

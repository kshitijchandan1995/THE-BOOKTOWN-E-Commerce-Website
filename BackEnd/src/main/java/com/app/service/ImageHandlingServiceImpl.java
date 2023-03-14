package com.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.web_product.ApiResponse;
import com.app.entity.User;
import com.app.entity.product;
import com.app.exception.RunTimeExceptionPlaceHolder;
import com.app.repository.ProductRepository;
import com.app.repository.UserRepository;


@Service
@Transactional
public class ImageHandlingServiceImpl implements ImageHandlingService {

	@Value("${content.upload.folder}")
	private String folderName;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProductRepository productRepo;
	
	@PostConstruct
	public void myInit() {
		System.out.println("in myInit " + folderName);
		File path = new File(folderName);
		if (!path.exists()) {
			path.mkdirs();
		} else
			System.out.println("folder alrdy exists....");
	}

	@Override
	public ApiResponse uploadImage(Long productId, MultipartFile imageFile) throws IOException {
		
		  Optional<product> productIdOptional = productRepo.findByproductId(productId);
		  product Product = productIdOptional.orElseThrow(() ->
	        new RunTimeExceptionPlaceHolder("productId  doesn't exist!!")
				    );

		String targetPath = folderName + File.separator +"ui"+Product.getProductId()+imageFile.getOriginalFilename();
		System.out.println(targetPath);
		Files.copy(imageFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
		
		Product.setImageId(targetPath);
		return new ApiResponse("Image Uploaded successfully!");
	}

	@Override
	public byte[] serveImage(Long productId) throws IOException {
		 Optional<product> productIdOptional = productRepo.findByproductId(productId);
		  product Product = productIdOptional.orElseThrow(() ->
	        new RunTimeExceptionPlaceHolder("productId  doesn't exist!!")
				    );
		String path = Product.getImageId();
		return Files.readAllBytes(Paths.get(path));
	}
}

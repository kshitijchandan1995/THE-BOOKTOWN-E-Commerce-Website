package com.app.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.app.dto.web_product.CreateProductRequest;
import com.app.dto.web_product.ProductResponse;
import com.app.dto.web_product.UpdateProductRequest;
import com.app.entity.product;

public interface ProductService {

	Long createProduct( CreateProductRequest createProductRequest);
	  
	  ProductResponse getProduct(Long productId);
	  void deleteProduct(Long productId);

	  void updateProduct(UpdateProductRequest updateProductRequest);

	  Page<product> findAllProducts(Pageable pageable);

	  Page<ProductResponse> getAllProducts(String sort, Integer page, Integer size);

	  List<product> getAllProductss();

	  List<product> getAllProductss(Long categoryId);
	}

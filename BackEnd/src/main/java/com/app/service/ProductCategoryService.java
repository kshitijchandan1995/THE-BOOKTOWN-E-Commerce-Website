package com.app.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import com.app.dto.web_product.CreateProductCategoryRequest;
import com.app.dto.web_product.UpdateProductCategoryRequest;
import com.app.entity.ProductCategory;


public interface ProductCategoryService {

  Long createProductCategory( CreateProductCategoryRequest createProductCategoryRequest);

  ProductCategory getProductCategory(Long productCategoryId);

  void deleteProductCategory(Long productCategoryId);

  void updateProductCategory(UpdateProductCategoryRequest updateProductCategoryRequest);

  Page<ProductCategory> getAllProductCategories(String sort, Integer page, Integer size);
}

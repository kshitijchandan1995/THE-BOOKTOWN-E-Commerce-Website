package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.dto.web_product.CreateProductRequest;
import com.app.dto.web_product.ProductResponse;
import com.app.dto.web_product.UpdateProductRequest;
import com.app.entity.ProductCategory;
import com.app.entity.Review;
import com.app.entity.product;
import com.app.repository.ProductCategoryRepository;
import com.app.repository.ProductRepository;
import com.app.repository.ReviewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;
    
    
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Long createProduct( CreateProductRequest createProductRequest) {
System.out.println();
        Optional<ProductCategory> productCategoryOptional =
                productCategoryRepository.findByproductCategoryId(createProductRequest.getProductCategoryId());

        ProductCategory productCategory = productCategoryOptional.orElseThrow(() -> new RuntimeException("ProductCategory doesn't exist!"));

        product Product = product.builder()
                .productName(createProductRequest.getProductName())
                .description(createProductRequest.getDescription())
                .availableItemCount(createProductRequest.getAvailableItemCount())
                .price(createProductRequest.getPrice())
                .productCategory(productCategory)
                .imageId(createProductRequest.getImageId())
                .build();


        product savedProduct = productRepository.save(Product);
        System.out.println("1"+savedProduct.getProductId());
        return savedProduct.getProductId();
    }
    
    
    @Override
    public ProductResponse getProduct(Long productId) {
        Optional<product> productOptional =
                productRepository.findByproductId(productId);

        product product = productOptional.orElseThrow(() -> new RuntimeException("Product Id doesn't exist!"));
        ProductResponse productResponse = objectMapper.convertValue(product, ProductResponse.class);
        populateRatingForProduct(productId, productResponse);
        return productResponse;
    }
//    //This way of setting rating for productResponse is not okay, But this is okay for now.
    private void populateRatingForProduct(Long productId, ProductResponse productResponse) {
        List<Review> reviewsForProduct = reviewService.getReviewsForProduct(productId);
        if (reviewsForProduct.size() > 0) {
            double sum = reviewsForProduct.stream().mapToDouble(Review::getRatingValue).sum();
            double rating = sum / reviewsForProduct.size();
            productResponse.setAverageRating(rating);
        }

        productResponse.setNoOfRatings(Math.toIntExact(reviewRepository.countAllByProductId(productId)));
    }
    @Override
    public void deleteProduct(Long productId) {

        productRepository.deleteById(productId);

    }

    @Override
    public void updateProduct(UpdateProductRequest updateProductRequest) {
    	System.out.println(updateProductRequest);
    	System.out.println("0");
        Optional<product> productOptional =
                productRepository.findById(updateProductRequest.getProductId());
System.out.println("1");
        //check weather product exists
        final product productExisting = productOptional.orElseThrow(() -> new RuntimeException("Product Id doesn't exist!"));
        System.out.println("2");
        productExisting.setProductId(updateProductRequest.getProductId());
        System.out.println("3");
        if (updateProductRequest.getProductName() != null) {
            productExisting.setProductName(updateProductRequest.getProductName());
        }
        System.out.println("4");
        if (updateProductRequest.getDescription() != null) {
            productExisting.setDescription(updateProductRequest.getDescription());
        }

        if (updateProductRequest.getPrice() != null) {
            productExisting.setPrice(updateProductRequest.getPrice());
        }

        if (updateProductRequest.getImageId() != null) {
            productExisting.setImageId(updateProductRequest.getImageId());
        }

        if (updateProductRequest.getProductCategoryId() != null) {
            Optional<ProductCategory> productCategoryOptional =
                    productCategoryRepository.findByproductCategoryId(updateProductRequest.getProductCategoryId());

            //check weather product category exists
            ProductCategory productCategory = productCategoryOptional.orElseThrow(() -> new RuntimeException("ProductCategory doesn't exist!"));
            productExisting.setProductCategory(productCategory);
        }

        if (updateProductRequest.getAvailableItemCount() != null) {
            productExisting.setAvailableItemCount(updateProductRequest.getAvailableItemCount());
        }

        productExisting.setCreatedAt(productExisting.getCreatedAt());

        productRepository.save(productExisting);
    }
    
    @Override
    public Page<ProductResponse> getAllProducts(String sort, Integer page, Integer size) {
        
        //set defaults
        if (size == null || size == 0) {
            size = 20;
        }
        
        //set defaults
        if (page == null || page == 0) {
            page = 0;
        }
        
        Pageable pageable;
        
        if (sort == null) {
            pageable = PageRequest.of(page, size);
        } else {
            Sort.Order order;
            
            try {
                String[] split = sort.split(",");
                
                Sort.Direction sortDirection = Sort.Direction.fromString(split[1]);
                order = new Sort.Order(sortDirection, split[0]).ignoreCase();
                pageable = PageRequest.of(page, size, Sort.by(order));
                
            } catch (Exception e) {
                throw new RuntimeException("Not a valid sort value, It should be 'fieldName,direction', example : 'productName,asc");
            }
            
        }
        Page<product> allProducts = productRepository.findAll(pageable);
        Page<ProductResponse> allProductsResponse = allProducts.map(product::fromEntity);
        allProductsResponse.forEach(productResponse -> populateRatingForProduct(productResponse.getProductId(), productResponse));

    
        return allProductsResponse;
    }


    @Override
    public Page<product> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
 

	@Override
	public List<product> getAllProductss() {
		
		List<product> allProductss = productRepository.findAll();
		return allProductss;
	}
	@Override
	public List<product> getAllProductss(Long categoryId) {

		ProductCategory cate=productCategoryRepository.findById(categoryId).get();
		List<product> allProductss = productRepository.findByproductCategory(cate);
		allProductss.forEach(System.out::println);
//		List<product> allProductsbycategory=allProductss.stream().filter(x->x.getProductCategory().equals(categoryId)).collect(Collectors.toList());
		return allProductss;
	}
    


}
    
    
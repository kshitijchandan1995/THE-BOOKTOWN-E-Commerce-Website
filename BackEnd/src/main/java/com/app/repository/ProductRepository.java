package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.ProductCategory;
import com.app.entity.product;

@Repository
public interface ProductRepository extends JpaRepository<product, Long> {
	
	Optional<product> findByproductId(Long productId);

	List<product> findByproductCategory(ProductCategory productId);
}


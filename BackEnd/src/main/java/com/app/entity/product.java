package com.app.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.dto.web_product.ProductResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "PRODUCT")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class product extends DateAudit {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "PRODUCT_ID", updatable = false, nullable = false)
	    private Long productId;
	 
	 
    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String description;
    private double price;

    @Column(name = "PRODUCT_IMAGE_ID")
    private String imageId;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_CATEGORY_ID")
    private ProductCategory productCategory;

    @Column(name = "AVAILABLE_ITEM_COUNT")
    private int availableItemCount;

    public String getProductCategory() {
        return productCategory.getProductCategoryName();
    }
    public static ProductResponse fromEntity(product product) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.convertValue(product, ProductResponse.class);
    }
    
}
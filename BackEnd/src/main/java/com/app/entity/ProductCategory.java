package com.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "PRODUCT_CATEGORY")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory extends DateAudit {
	
	
	private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "PRODUCT_CATEGORY_ID", updatable = false, nullable = false)
    private Long productCategoryId;
	
	
    @Column(name = "PRODUCT_CATEGORY_NAME", nullable = false)
    private String productCategoryName;

    @OneToMany(
            mappedBy = "productCategory",
            cascade = CascadeType.ALL
    )
    private List<product> products;
    private String description;
}
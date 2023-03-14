package com.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ORDER_ITEM")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends DateAudit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID", updatable = false, nullable = false)
    private Long orderItemId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    @JsonIgnore
    private Order order;
    
    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;
    
    @Column(name = "ORDER_ITEM_PRICE", nullable = false)
    private double orderItemPrice;

    @Column(name = "ORDER_EXTENDED_PRICE", nullable = false)
    private double orderExtendedPrice;
    
}

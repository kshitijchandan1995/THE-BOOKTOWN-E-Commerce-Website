package com.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_PAYMENT_CUSTOMER")
@Builder
public class UserPaymentCustomer extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long userPaymentCustomerId;

    @Column(name = "USER_ID", nullable = false, unique = true)
    private String userId;

    @Column(name = "USER_NAME", nullable = false, unique = true)
    private String userName;

    @Column(name = "PAYMENT_CUSTOMER_ID", nullable = false, unique = true)
    private String paymentCustomerId;
    
}
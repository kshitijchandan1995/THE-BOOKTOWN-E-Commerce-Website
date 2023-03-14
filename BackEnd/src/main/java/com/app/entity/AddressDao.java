package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "ADDRESS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDao extends DateAudit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ADDRESS_ID", updatable = false, nullable = false)
    private Long addressId;
    
    
//    @OneToOne
//    @JoinColumn(name = "USER_ID")
    @Column(name = "USER_ID",nullable = false)
    private Long userId;
    
    @Column(name = "ADDRESS_LINE1", nullable = false)
    private String addressLine1;
    
    @Column(name = "ADDRESS_LINE2")
    private String addressLine2;
    
    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STATE", nullable = false)
    private String state;
    
    @Column(name = "POSTAL_CODE", nullable = false)
    private String postalCode;
    
//    @Pattern(regexp = "[A-Z]{2}", message = "2-letter ISO country code required")
    
    private String country;

    @Column(name = "PHONE")
    private String phone;
}
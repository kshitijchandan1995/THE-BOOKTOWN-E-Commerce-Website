package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.AddressDao;

public interface AddressRepository extends JpaRepository<AddressDao, Long> {

    Optional<List<AddressDao>> findByuserId(Long userId);

    Optional<AddressDao> findByAddressId(Long addressId);

    Boolean existsByUserId(Long userId);
//    
	
}
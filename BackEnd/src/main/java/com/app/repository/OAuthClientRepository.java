package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.OAuthClient;

public interface OAuthClientRepository extends JpaRepository<OAuthClient, Long> {

}




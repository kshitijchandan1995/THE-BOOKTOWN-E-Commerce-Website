package com.app.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.User;


@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

 Optional<User> findByUserName(String username);

  Optional<User> findByUserNameOrEmail(String uName, String eMail);

  Optional<User> findByUserId(Long userId);

  void deleteByUserId(Long userId);

  Boolean existsByUserName(String userName);

  Boolean existsByEmail(String email);

}
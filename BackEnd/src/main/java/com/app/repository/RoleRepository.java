package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByRoleName(String name);
//
  Boolean existsByRoleName(String roleName);
//
  @Override
  List<Role> findAll();
}

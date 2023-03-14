package com.app.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.web_product.CreateRoleRequest;
import com.app.entity.Role;
import com.app.repository.RoleRepository;


@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  RoleRepository roleRepository;


  @Override
  public Long createRole(CreateRoleRequest createRoleRequest) {

    Role role = Role.builder()
        .roleName(createRoleRequest.getRoleName())
        .roleDescription(createRoleRequest.getRoleDescription())
        .build();

    Role savedRole = roleRepository.save(role);
    return savedRole.getId();
  }

  @Override
  public List<Role> getAllRoles() {
    List<Role> allRoles = roleRepository.findAll();
    return allRoles;
  }
}

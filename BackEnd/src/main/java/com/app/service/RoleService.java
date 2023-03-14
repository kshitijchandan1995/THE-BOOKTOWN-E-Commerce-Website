package com.app.service;



import java.util.List;

import com.app.dto.web_product.CreateRoleRequest;
import com.app.entity.Role;

public interface RoleService {

  Long createRole(CreateRoleRequest createRoleRequest);

  List<Role> getAllRoles();
}

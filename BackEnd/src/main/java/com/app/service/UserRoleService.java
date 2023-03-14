package com.app.service;

import com.app.dto.web_product.MapRoleToUsersRequest;
import com.app.dto.web_product.MapUserToRolesRequest;

public interface UserRoleService {

	  void mapUserToRoles(String userNameOrEmail, MapUserToRolesRequest mapUserToRolesRequest);

	  void removeRolesFromUser(String userNameOrEmail, MapUserToRolesRequest mapUserToRolesRequest);

	  void mapRoleToUsers(String roleName, MapRoleToUsersRequest mapRoleToUsersRequest);
	}

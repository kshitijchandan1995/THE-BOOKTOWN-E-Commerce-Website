package  com.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.web_product.MapRoleToUsersRequest;
import com.app.dto.web_product.MapUserToRolesRequest;
import com.app.service.UserRoleService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserRoleController {

  @Autowired
  UserRoleService userRoleService;

  @PostMapping("/user/{userNameOrEmail}/roles")
  public void mapUserToRoles(@PathVariable("userNameOrEmail") String userNameOrEmail,
      @RequestBody  MapUserToRolesRequest mapUserToRolesRequest) {
    userRoleService.mapUserToRoles(userNameOrEmail, mapUserToRolesRequest);

  }

  @PostMapping("/role/{roleName}/users")
  public void mapRoleToUsers(@PathVariable("roleName") String roleName,
      @RequestBody  MapRoleToUsersRequest mapRoleToUsersRequest) {

    userRoleService.mapRoleToUsers(roleName, mapRoleToUsersRequest);

  }
}

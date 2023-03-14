package com.app.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.dto.web_product.CreateRoleRequest;
import com.app.entity.Role;
import com.app.service.RoleService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RoleController {

  @Autowired
  private RoleService roleService;

  @PostMapping("/role")
  public ResponseEntity<?> createRole(@RequestBody  CreateRoleRequest createRoleRequest) {

    Long userId = roleService.createRole(createRoleRequest);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{roleId}")
        .buildAndExpand(userId).toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping("/roles")
  public ResponseEntity<?> getAllRoles() {
    List<Role> allRoles = roleService.getAllRoles();
    return ResponseEntity.ok(allRoles);

  }

  //TODO CRUD for role
}

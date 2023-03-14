package com.app.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.dto.web_product.CreateUserRequest;
import com.app.dto.web_product.GetUserInfoResponse;
import com.app.dto.web_product.GetUserResponse;
import com.app.dto.web_product.UpdateUserRequest;
import com.app.dto.web_product.UpdateUserRequestFromAdmin;
import com.app.service.IUserService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

  @Autowired
  private IUserService userService;

  @PostMapping("/user")
//  @PreAuthorize("hasAuthority('ADMIN_USER')")
  public ResponseEntity<?> createUser(@RequestBody  CreateUserRequest createUserRequest) {

    long userId = userService.registerUser(createUserRequest);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest().path("/{userId}")
        .buildAndExpand(userId).toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping("/user")
//  @PreAuthorize("hasAuthority('ADMIN_USER')")
  public ResponseEntity<GetUserResponse> getUser(
      @RequestParam("userName") Optional<String> userName
      , @RequestParam("userId") Optional<Long> userId) {

    GetUserResponse user = null;
    if (userName.isPresent()) {
      user = userService.getUserByUserName(userName.get());
    } else if (userId.isPresent()) {
      user = userService.getUserByUserId(userId.get());
    }
    return ResponseEntity.ok(user);
  }

  @PutMapping("/user/{userId}")
//  @PreAuthorize("hasAuthority('ADMIN_USER')")
  public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId,
                                      @RequestBody  UpdateUserRequestFromAdmin updateUserRequestFromAdmin) {

    userService.updateUser(userId,updateUserRequestFromAdmin);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/users")
//  @PreAuthorize("hasAuthority('ADMIN_USER')")
  public ResponseEntity<List<GetUserResponse>> getAllUsers() {
	  System.out.println("inside users controller");
    List<GetUserResponse> allUsers = userService.getAllUsers();
//    System.out.println();
//    List<GetUserResponse> users=allUsers.stream().filter(x-> !(x.getRoles()).contains("Role_Admin")).collect(Collectors.toList());
//   allUsers.forEach(System.out::println);
 
    return ResponseEntity.ok(allUsers);
    
  }

  @GetMapping("/userInfo")
  public ResponseEntity<GetUserInfoResponse> getUserInfo() {
    GetUserInfoResponse userInfo = userService.getUserInfo();
    return new ResponseEntity<>(userInfo, HttpStatus.OK);
  }

  @PutMapping("/userInfo")
  public ResponseEntity<?> updateUserInfo(@RequestBody  UpdateUserRequest updateUserRequest) {
    userService.updateUserInfo(updateUserRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/user/{userId}")
//  @PreAuthorize("hasAuthority('ADMIN_USER')")
  public ResponseEntity<?> deleteUserById(@PathVariable("userId") Long userId){
    userService.deleteUserById(userId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}

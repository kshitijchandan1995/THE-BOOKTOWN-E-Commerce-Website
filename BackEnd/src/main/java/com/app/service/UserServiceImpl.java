package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.web_product.ApiResponse;
import com.app.dto.web_product.CreateUserRequest;
import com.app.dto.web_product.GetUserInfoResponse;
import com.app.dto.web_product.GetUserResponse;
import com.app.dto.web_product.MapUserToRolesRequest;
import com.app.dto.web_product.UpdateUserRequest;
import com.app.dto.web_product.UpdateUserRequestFromAdmin;
import com.app.entity.Role;
import com.app.entity.User;
import com.app.exception.Error;
import com.app.exception.ErrorResponse;
import com.app.exception.RunTimeExceptionPlaceHolder;
import com.app.exception.SuccessCodeWithErrorResponse;
import com.app.repository.OTPRepository;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService{

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
	private OTPRepository otpRepo;
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private UserRoleService userRoleService;

  @Override
  public long registerUser(CreateUserRequest createUserRequest) {

    String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());

    if (userRepository.existsByUserName(createUserRequest.getUserName())) {
      throw new RunTimeExceptionPlaceHolder("Username is already taken!!");
    }

    if (userRepository.existsByEmail(createUserRequest.getEmail())) {
      throw new RunTimeExceptionPlaceHolder("Email address already in use!!");
    }

    ErrorResponse errorResponse = ErrorResponse.builder()
        .uuid(UUID.randomUUID())
        .errors(new ArrayList<>())
        .build();

    List<Role> validRoles = new ArrayList<>();

    createUserRequest.getRoleNames().forEach(roleName -> {

      //if role exists add to list and persist, else add to error response persist valid roles and send
      // response containing invalid roles.
      roleRepository.findByRoleName(roleName).<Runnable>map(role -> () -> validRoles.add(role))
          .orElse(() -> {
            Error error = Error.builder()
                .code("400")
                .message(roleName + " role doesn't exist")
                .build();
            errorResponse.getErrors().add(error);
          })
          .run();
    });
    User user = User.builder()
            .userName(createUserRequest.getUserName())
            .email(createUserRequest.getEmail())
            .firstName(createUserRequest.getFirstName())
            .lastName(createUserRequest.getLastName())
            .password(encodedPassword)
            .Roles(new ArrayList<>(validRoles))
            .build();

        User savedUser = userRepository.save(user);

        if (!errorResponse.getErrors().isEmpty()) {
          throw new SuccessCodeWithErrorResponse(savedUser.getUserId(), errorResponse);
        }
        System.out.println(savedUser.getRoles()+"roles saved user");
        return savedUser.getUserId();
  }

  @Override
  public GetUserResponse getUserByUserName(String userName) {

    Optional<User> userNameOrEmailOptional = userRepository
        .findByUserNameOrEmail(userName, userName);
    User userByUserName = userNameOrEmailOptional.orElseThrow(() ->
        new RunTimeExceptionPlaceHolder("UserName or Email doesn't exist!!")
    );

    return GetUserResponse.builder()
        .userId(userByUserName.getUserId())
        .userName(userByUserName.getUserName())
        .firstName(userByUserName.getFirstName())
        .lastName(userByUserName.getLastName())
        .email(userByUserName.getEmail())
        .roles(userByUserName.getRoles())
        .build();
  }

  @Override
  public GetUserResponse getUserByUserId(Long userId) {
    Optional<User> userIdOptional = userRepository.findByUserId(userId);
    User userById = userIdOptional.orElseThrow(() ->
        new RunTimeExceptionPlaceHolder("UserName or Email doesn't exist!!")
    );

    return GetUserResponse.builder()
        .userId(userById.getUserId())
        .userName(userById.getUserName())
        .firstName(userById.getFirstName())
        .lastName(userById.getLastName())
        .email(userById.getEmail())
        .roles(userById.getRoles())
        .build();
  }

  @Override
  public GetUserInfoResponse getUserInfo() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = (String) authentication.getPrincipal();

    GetUserResponse userByUserName = getUserByUserName(userName);

    return GetUserInfoResponse.builder()
        .userId(userByUserName.getUserId())
        .userName(userByUserName.getUserName())
        .firstName(userByUserName.getFirstName())
        .lastName(userByUserName.getLastName())
        .email(userByUserName.getEmail())
        .build();

  }

  @Override
  public void updateUserInfo(UpdateUserRequest updateUserRequest) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = (String) authentication.getPrincipal();

    Optional<User> userNameOrEmailOptional = userRepository.findByUserNameOrEmail(userName, userName);

    User userByUserName = userNameOrEmailOptional.orElseThrow(() ->
            new RunTimeExceptionPlaceHolder("UserName or Email doesn't exist!!")
    );

    if(updateUserRequest.getFirstName()!=null){
      userByUserName.setFirstName(updateUserRequest.getFirstName());
    }
    if(updateUserRequest.getLastName()!=null){
      userByUserName.setLastName(updateUserRequest.getLastName());
    }
    if(updateUserRequest.getPassword()!=null){
      String encodedPassword = passwordEncoder.encode(updateUserRequest.getPassword());
      userByUserName.setPassword(encodedPassword);
    }
    if(updateUserRequest.getEmail()!=null){
      userByUserName.setEmail(updateUserRequest.getEmail());
    }

    userRepository.save(userByUserName);
  }

  @Override
  public void deleteUserById(Long userId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = (String) authentication.getPrincipal();
    GetUserResponse userByUserId = getUserByUserId(userId);

    if(userName.equals(userByUserId.getUserName())){
      throw new RunTimeExceptionPlaceHolder("You cannot delete your own account!");
    }

    userRepository.deleteByUserId(userId);
  }

  @Override
  public List<GetUserResponse> getAllUsers() {

	  
    Iterable<User> all = userRepository.findAll();
    List<GetUserResponse> allUsers = new ArrayList<>();
    all.iterator().forEachRemaining(u->{
      GetUserResponse userResponse = GetUserResponse.builder()
              .userId(u.getUserId())
              .userName(u.getUserName())
              .firstName(u.getFirstName())
              .lastName(u.getLastName())
              .email(u.getEmail())
              .roles(u.getRoles())
              .build();

      System.out.println("inside users"+all.toString());
      allUsers.add(userResponse);
    });

    return allUsers;
  }

  @Override
  public void updateUser(Long userId, UpdateUserRequestFromAdmin updateUserRequestFromAdmin) {

    Optional<User> existingUser = userRepository.findByUserId(userId);

    User user = existingUser.orElseThrow(() ->
            new RunTimeExceptionPlaceHolder("UserId doesn't exist!!")
    );

    if(updateUserRequestFromAdmin.getFirstName()!=null){
      user.setFirstName(updateUserRequestFromAdmin.getFirstName());
    }
    if(updateUserRequestFromAdmin.getLastName()!=null){
      user.setLastName(updateUserRequestFromAdmin.getLastName());
    }
    if(updateUserRequestFromAdmin.getEmail()!=null){
      user.setEmail(updateUserRequestFromAdmin.getEmail());
    }

    if(user.getRoles().size()>0){
      MapUserToRolesRequest mapUserToRolesRequest = new MapUserToRolesRequest();
      List<String> existingRoles = user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());
      mapUserToRolesRequest.setRoleNames(existingRoles);
      userRoleService.removeRolesFromUser(user.getUserName(), mapUserToRolesRequest);
    }

    if (updateUserRequestFromAdmin.getRoles().size() > 0) {
      MapUserToRolesRequest mapUserToRolesRequest = new MapUserToRolesRequest();
      mapUserToRolesRequest.setRoleNames(updateUserRequestFromAdmin.getRoles());
      userRoleService.mapUserToRoles(user.getUserName(), mapUserToRolesRequest);
    }

    userRepository.save(user);
  }

@Override
public List<Role> getRolebyusername(String Username) {
	
	
	Optional<User> userNameOrEmailOptional = userRepository
	        .findByUserName(Username);

	    User user = userNameOrEmailOptional.orElseThrow(() ->
	        new RunTimeExceptionPlaceHolder("UserNameOrEmail doesn't exist!!")
	    );
	    List<Role> roles = user.getRoles();
	    
	return roles;
}

@Override
public ApiResponse updateUserPassword(String userName, String password) {
	User user=userRepository.findByUserName(userName).get();
	System.out.println("Password   ------  "+password);
	user.setPassword(passwordEncoder.encode(password));
	userRepository.save(user);
	otpRepo.deleteById(userName);
	return new ApiResponse("Updated Password Sucessfully");
}
  
}

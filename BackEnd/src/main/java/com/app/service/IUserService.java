package com.app.service;

import java.util.List;

import com.app.dto.web_product.ApiResponse;
import com.app.dto.web_product.CreateUserRequest;
import com.app.dto.web_product.GetUserInfoResponse;
import com.app.dto.web_product.GetUserResponse;
import com.app.dto.web_product.UpdateUserRequest;
import com.app.dto.web_product.UpdateUserRequestFromAdmin;
import com.app.entity.Role;

public interface IUserService {
	long registerUser(CreateUserRequest createUserReques);

	  GetUserResponse getUserByUserName(String userName);

	  GetUserResponse getUserByUserId(Long userId);

	  GetUserInfoResponse getUserInfo();

	  void updateUserInfo(UpdateUserRequest updateUserRequest);

	  void deleteUserById(Long userId);

	  List<GetUserResponse> getAllUsers();

	  void updateUser(Long userId, UpdateUserRequestFromAdmin updateUserRequestFromAdmin);
	  
	  List<Role>  getRolebyusername(String Username);

	ApiResponse updateUserPassword(String userName, String password);
	  
}

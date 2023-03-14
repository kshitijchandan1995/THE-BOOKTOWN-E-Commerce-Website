package com.app.dto.web_product;

import java.util.List;
import java.util.Set;

import com.app.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserResponse {

  private long userId;
  private String userName;
  private String firstName;
  private String lastName;
  private String email;
  private List<Role> roles;

}

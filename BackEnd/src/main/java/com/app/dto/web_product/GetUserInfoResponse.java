package com.app.dto.web_product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserInfoResponse {

  private long userId;
  private String userName;
  private String firstName;
  private String lastName;
  private String email;

}

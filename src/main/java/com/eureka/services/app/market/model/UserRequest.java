package com.eureka.services.app.market.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest {
  @NotEmpty
  private String nombres;
  @NotEmpty
  private String apellidos;
  @NotEmpty
  @Email
  private String email;
  @NotEmpty
  private String password;  
}

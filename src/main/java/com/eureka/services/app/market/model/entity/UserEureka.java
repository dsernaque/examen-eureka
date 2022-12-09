package com.eureka.services.app.market.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.eureka.services.app.market.model.UserRequest;
import com.eureka.services.app.market.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
@Builder
@AllArgsConstructor
public class UserEureka {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int    id;
  private String nombres;
  private String apellidos;
  @Column(unique = true)
  private String email;
  private String password;
  private String role;
  
  public static UserEureka from(final UserRequest userData) {
    return UserEureka.builder()
        .nombres(userData.getNombres())
        .apellidos(userData.getApellidos())
        .email(userData.getEmail())
        .password(userData.getPassword())
        .role(Constants.ROLE_USER)
        .build();
  }
}

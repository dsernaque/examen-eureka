package com.eureka.services.app.market.service;

import com.eureka.services.app.market.model.UserRequest;

public interface UserService {
  public String signIn(UserRequest request);
}

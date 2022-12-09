package com.eureka.services.app.market.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.services.app.market.model.UserRequest;
import com.eureka.services.app.market.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
  
  @Autowired
  private UserService userService;
  
  @PostMapping(path = "/signIn",
               produces = MediaType.APPLICATION_JSON_VALUE)
  public String signIn(@Valid @RequestBody UserRequest userData) {
    log.info("Starting UserController.signIn method");
    return userService.signIn(userData);
  }
  
}

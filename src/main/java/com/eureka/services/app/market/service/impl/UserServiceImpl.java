package com.eureka.services.app.market.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.eureka.services.app.market.model.UserRequest;
import com.eureka.services.app.market.model.entity.UserEureka;
import com.eureka.services.app.market.repository.UserRepository;
import com.eureka.services.app.market.service.UserService;
import com.eureka.services.app.market.util.Constants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
  
  @Autowired
  private UserRepository userRepository;
  
  @Override
  public String signIn(UserRequest request) {
    log.info("Starting UserService.signIn method");
    String     token = getJWTToken(request.getEmail());
    UserEureka data  = UserEureka.from(request);
    userRepository.save(data);
    return token;
  }
  
  private String getJWTToken(String username) {
    log.info("Starting UserService.getJWTToken method");
    String                 secretKey          = "mySecretKey";
    List<GrantedAuthority> grantedAuthorities = AuthorityUtils
        .commaSeparatedStringToAuthorityList(Constants.ROLE_USER);
    
    return Jwts
        .builder()
        .setId("eurekaKey")
        .setSubject(username)
        .claim("authorities",
            grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 600000))
        .signWith(SignatureAlgorithm.HS512,
            secretKey.getBytes())
        .compact();
  }
}

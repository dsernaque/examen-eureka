package com.eureka.services.app.market.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.eureka.services.app.market.model.entity.UserEureka;
import com.eureka.services.app.market.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {
  
  @Autowired
  private UserRepository userRepository;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("Starting UserDetailServiceImpl.loadUserByUsername method");
    /*
     * List<UserData> userList = userRepository.findByEmail(username);
     * 
     * UserData userLogin;
     * 
     * if (!userList.isEmpty()) { userLogin = userList.get(0); } else { throw new
     * UsernameNotFoundException(username); }
     * 
     * User.UserBuilder userBuilder = User.withUsername(username);
     * userBuilder.password(userLogin.getApikey()); return userBuilder.build();
     */
    return userRepository.findByEmail(username)
        .map(this::map)
        .orElseThrow(() -> new UsernameNotFoundException(
            "user " + username + " not found "));
  }
  
  private UserDetails map(UserEureka userData) {
    log.info("Starting UserDetailServiceImpl.map method");
    List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userData.getRole());
    User                   user        = new User(userData.getNombres(), userData.getPassword(),
        true, true, true, true, authorities);
    log.info("UserData: " + user.getUsername());
    return user;
    
  }
  
}
